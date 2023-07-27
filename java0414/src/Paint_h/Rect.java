package Paint_h;

import java.awt.Graphics;

public class Rect extends FigureInfo {

	@Override
	public void draw(Graphics g) {
		g.setColor(thisColor);
		g.drawRect(startP.x,startP.y, 
				endP.x-startP.x, endP.y-startP.y);
	}
}
