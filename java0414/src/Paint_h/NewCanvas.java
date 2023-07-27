package Paint_h;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class NewCanvas extends Canvas {

	private Color nowColor = Color.BLACK; // 현재 그리는 도형의 색을 결정...

	private Point startPoint = new Point();
	private Point ingPoint = new Point();
	private Point nowPoint = new Point();

	static NewCanvas singleCanvas = new NewCanvas();
	ArrayList<FigureInfo> figureList = new ArrayList<>();

	FigureInfo newFigure = new Line();

	private String figureType = "선";

	Boolean ingFlag = false;

	private NewCanvas() {
		this.setBackground(Color.yellow);
	}

	public void setStartP(Point in) { // mousePressed
		newFigure = newFigureObj();
		newFigure.startP = in;
		newFigure.thisColor = nowColor;
	}

	public FigureInfo newFigureObj() {
		switch (figureType) {
		case "원": return new Oval();
		case "선": return new Line();
		case "사각": return new Rect();
		default : return null;
		}
	}

	public void ingP(Point ing) { // mouseDragged
		newFigure.endP = ing;
	}

	public void setEndP(Point end) { // mouseReleased
		newFigure.endP = end;
		figureList.add(newFigure);
	}

	public void setFigure(String f) {
		this.figureType = f;
	}

	public void setColor(Color c) {
		this.nowColor = c;
	}

	public static NewCanvas getSingleton() {
		return singleCanvas;
	}

	public void paint(Graphics g) {
		for (int i = 0; i < figureList.size(); i++) {
			figureList.get(i).draw(g);
		}

		if (ingFlag) {
			newFigure.draw(g);
		}

	}
}
