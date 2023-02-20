package design.notification;


import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Taskbar;
import java.awt.Window;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;


public class BadgeIcon extends BufferedImage {
	
	private final int iconSize = 22;
	private final Color background;
	private final Color foreground;
	
	public BadgeIcon( String text , Color background , Color foreground ) {
		
		super( 24 , 24 , BufferedImage.TYPE_INT_ARGB );
		this.background = background;
		this.foreground = foreground;
		drawText( text );
		
	}
	
	
	private void drawText( String text ) {
		
		Graphics2D g2 = createGraphics();
		g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON );
		g2.setRenderingHint( RenderingHints.KEY_TEXT_ANTIALIASING , RenderingHints.VALUE_TEXT_ANTIALIAS_ON );
		g2.setRenderingHint( RenderingHints.KEY_FRACTIONALMETRICS , RenderingHints.VALUE_FRACTIONALMETRICS_ON );
		g2.setColor( background );
		g2.fillOval( 0 , 0 , iconSize , iconSize );
		Font font = g2.getFont().deriveFont( 16.5f );
		g2.setFont( font );
		FontMetrics fm = g2.getFontMetrics();
		Rectangle2D r = fm.getStringBounds( text , g2 );
		double x = (iconSize - r.getWidth()) / 2;
		double y = ((iconSize - r.getHeight()) / 2) + fm.getAscent();
		g2.setColor( foreground );
		g2.drawString( text , (float) x , (float) y );
		g2.dispose();
		
	}
	
	
	public static void showWindowBadge( Window window , String text , Color background , Color foreground ) {
		
		if ( Taskbar.getTaskbar().isSupported( Taskbar.Feature.ICON_BADGE_IMAGE_WINDOW ) ) {
			
			Taskbar taskbar = Taskbar.getTaskbar();
			taskbar.setWindowIconBadge( window , new BadgeIcon( text , background , foreground ) );
			
		}
		
	}
	
}
