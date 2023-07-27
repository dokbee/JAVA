package Paint_h;

import java.awt.Graphics;

public class Line extends FigureInfo{

	@Override
	public void draw(Graphics g) {
		g.setColor(thisColor);
		g.drawLine(startP.x,startP.y, 
				endP.x, endP.y);

	}
}
