package Paint;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class NewCanvas extends Canvas{
	
	private Color nowColor = Color.BLACK;  // 현재 그리는 도형의 색을 결정...

	private Point startPoint=new Point();
	private Point ingPoint=new Point();
	private Point nowPoint=new Point();
	
	static NewCanvas singleCanvas = new NewCanvas();
	ArrayList<FigureInfo> figureList = new ArrayList<>();
	
	FigureInfo newFigure=null;
	
	private String nowfigure="선";//버튼 타입을 지정하는 변수
	
	Boolean ingFlag = false;
	
	private NewCanvas() {
		this.setBackground(Color.yellow);
	}
	
	public void setFigure(String f) {
		this.nowfigure=f;
		//System.out.println("현재 nowFigure"+this.nowfigure);
	}
	public void setStartP(Point in) {  //mousePressed
		newFigure = new FigureInfo();
		newFigure.figureType=this.nowfigure;
		newFigure.startP=in;
		newFigure.thisColor=nowColor;
		//this.startPoint=in;
	}
	public void ingP(Point ing) {  //mouseDragged
		this.ingPoint=ing;
	}
	
	public void setEndP(Point end) { //mouseReleased
		newFigure.endP=end;
		figureList.add(newFigure);
		//this.nowPoint=end;
	}
	public void setColor(Color c) {
		this.nowColor=c;
	}
	public static NewCanvas getSingleton() {
		return singleCanvas;
	}
	
	public void paint(Graphics g) {
		for(int i=0; i<figureList.size();i++) {
			FigureInfo nowFigure=figureList.get(i);
			g.setColor(nowFigure.thisColor);
			if(nowFigure.figureType.equals("원")) {
				g.drawOval(nowFigure.startP.x,nowFigure.startP.y,
						nowFigure.endP.x-nowFigure.startP.x,nowFigure.endP.y-nowFigure.startP.y);
			}else if(nowFigure.figureType.equals("사각")) {
				g.drawRect(nowFigure.startP.x,nowFigure.startP.y,
						nowFigure.endP.x-nowFigure.startP.x,nowFigure.endP.y-nowFigure.startP.y);
			}else if(nowFigure.figureType.equals("선")) {
				g.drawLine(nowFigure.startP.x,nowFigure.startP.y, 
						nowFigure.endP.x, nowFigure.endP.y);
			}
		}
		
		if(ingFlag) {
			if(newFigure.figureType.equals("원")) {
				g.drawOval(newFigure.startP.x,newFigure.startP.y,
						ingPoint.x-newFigure.startP.x,ingPoint.y-newFigure.startP.y);
			}else if(newFigure.figureType.equals("사각")) {
				g.drawRect(newFigure.startP.x,newFigure.startP.y,
						ingPoint.x-newFigure.startP.x,ingPoint.y-newFigure.startP.y);
			}else if(newFigure.figureType.equals("선")) {
				g.drawLine(newFigure.startP.x,newFigure.startP.y, 
						ingPoint.x, ingPoint.y);
			}
		}
		
		
	}
}












