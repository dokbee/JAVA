package Paint;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Main extends JFrame implements ActionListener {

	JMenuBar menuBar;
	JMenu file, tool, bgColor;
	JMenuItem miOpen, miSave, miExit, black;

	JPanel btnP = new JPanel();
	JButton circle, rectangle, line, polygon;
	JButton setColor;
	Color thisColor = Color.BLACK; // 팔레트에서 색상을 받아 오기 위한 참조변수

	EventHandler mHandler = new EventHandler();
	BtnActionHandler bHandler = new BtnActionHandler();
	NewCanvas canvas = NewCanvas.getSingleton();

	BufferedImage b = null;

	Main() {
		this.setBounds(200, 200, 400, 400);
		menu();
		figure();

		canvas.addMouseListener(mHandler);
		canvas.addMouseMotionListener(mHandler);

		this.add(btnP, "North");
		this.add(canvas, "Center");
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	}

	public void menu() {
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		file = new JMenu("File");
		menuBar.add(file);

		miOpen = new JMenuItem("open");
		file.add(miOpen);

		file.addSeparator();

		miSave = new JMenuItem("Save");
		file.add(miSave);

		miExit = new JMenuItem("Exit");
		file.add(miExit);

		miOpen.addActionListener(this);
		miSave.addActionListener(this);
		miExit.addActionListener(this);
	}

	public void figure() {
		circle = new JButton("원");
		rectangle = new JButton("사각");
		line = new JButton("선");
		polygon = new JButton("다각");
		setColor = new JButton("색상");

		circle.addActionListener(bHandler);
		rectangle.addActionListener(bHandler);
		line.addActionListener(bHandler);
		polygon.addActionListener(bHandler);
		setColor.addActionListener(bHandler);

		btnP.add(line);
		btnP.add(circle);
		btnP.add(rectangle);
		btnP.add(polygon);
		btnP.add(setColor);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main m = new Main();
		m.setVisible(true);
	}

	class EventHandler extends MouseAdapter {
		@Override
		public void mouseDragged(MouseEvent m) {
			// TODO Auto-generated method stub'
			canvas.ingFlag = true;
			canvas.ingP(m.getPoint());
			// canvas.setEndP(m.getPoint());
			System.out.println(m.getX() + "/" + m.getY());
			canvas.repaint();
		}

		public void mousePressed(MouseEvent m) { // 처음클릭
			canvas.setStartP(m.getPoint());
		}

		public void mouseReleased(MouseEvent m) { // 드리그 후 버튼을 놓을 때
			canvas.setEndP(m.getPoint());
			canvas.repaint();
			canvas.ingFlag = false;
		}
	}

	class BtnActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource().equals(setColor)) {
				System.out.println("aaa");
				JColorChooser chooser = new JColorChooser();
				thisColor = chooser.showDialog(null, "Color", Color.YELLOW);
				System.out.println(thisColor);

				if (thisColor != null) {
					canvas.setColor(thisColor);
					canvas.repaint();
				}
			}else {
				canvas.setFigure(e.getActionCommand());
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Save")) {
			FileDialog dialog = new FileDialog(this, "저장", FileDialog.SAVE);
			dialog.setDirectory(".");
			dialog.setVisible(true);

			if (dialog.getFile() == null)
				return;

			String fileName = dialog.getDirectory() + dialog.getFile() + ".png";

			try {
				Dimension d = canvas.getSize();
				b = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_RGB);
				canvas.paintAll(b.getGraphics());
				ImageIO.write(b, "PNG", new File(fileName));
				JOptionPane.showMessageDialog(this, "저장완료");
			} catch (IOException e1) {

			}

		}
	}

}
