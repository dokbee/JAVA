package Paint_h;

import java.awt.Graphics;

public class Oval extends FigureInfo {
	@Override
	public void draw(Graphics g) {
		g.setColor(thisColor);
		g.drawOval(startP.x,startP.y, 
				endP.x-startP.x, endP.y-startP.y);
	}
}
