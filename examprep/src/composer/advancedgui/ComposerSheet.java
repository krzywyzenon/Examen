package composer.advancedgui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JTextArea;

import composer.advancedgui.shapes.EighthNote;
import composer.advancedgui.shapes.FullNote;
import composer.advancedgui.shapes.HalfNote;
import composer.advancedgui.shapes.NoteDrawing;
import composer.advancedgui.shapes.SharpMarking;
import composer.advancedgui.shapes.QuarterNote;
import composer.data.Lengths;

class ComposerSheet extends JComponent
{
	
	private static final long serialVersionUID = 1L;
	Image image;
	Graphics2D graphics2D;
	int currentX, currentY, oldX, oldY;
	private int allowedX = 80;
	
	private int pageDisplayed = 1;
	
	private boolean IS_RELEASED = true;
	
	private boolean isInverted = false;
	
	EighthNote eighthNote = new EighthNote();
	QuarterNote quarterNote = new QuarterNote(); 
	HalfNote halfNote = new HalfNote();
	FullNote fullNote = new FullNote();
	SharpMarking sharpMarking = new SharpMarking();
	NoteDrawing currentNote;
	public ComposerSheet(){
		
	}
	
	public ComposerSheet(final JTextArea text){
		super();

		setDoubleBuffered(false);

		addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				oldX = e.getX();
				oldY = e.getY();
				if(GuiHelper.isCursorWithinLimits(e.getX(), e.getY(), GuiHelper.getFirstBoxStartingPoint(), GuiHelper.getBoxVerticalStartingPoint()))
				{
					IS_RELEASED = false;
					currentNote = eighthNote;
					SongProcessor.setLENGTH(Lengths.EIGHT);
				}
				else if(GuiHelper.isCursorWithinLimits(e.getX(), e.getY(), GuiHelper.getSecondBoxStartingPoint(), GuiHelper.getBoxVerticalStartingPoint()))
				{
					IS_RELEASED = false;
					currentNote = quarterNote;
					SongProcessor.setLENGTH(Lengths.QUARTER);
				}
				else if(GuiHelper.isCursorWithinLimits(e.getX(), e.getY(), GuiHelper.getThirdBoxStartingPoint(), GuiHelper.getBoxVerticalStartingPoint()))
				{
					IS_RELEASED = false;
					currentNote = halfNote;	
					SongProcessor.setLENGTH(Lengths.HALF);
				}
				else if(GuiHelper.isCursorWithinLimits(e.getX(), e.getY(),  GuiHelper.getFourthBoxStartingPoint(), GuiHelper.getBoxVerticalStartingPoint()))
				{
					IS_RELEASED = false;
					currentNote = fullNote;
					SongProcessor.setLENGTH(Lengths.WHOLE);
				}
				else if(GuiHelper.isCursorWithinLimits(e.getX(), e.getY(), GuiHelper.getFifthBoxStartingPoint(), GuiHelper.getBoxVerticalStartingPoint()))
				{
					IS_RELEASED = false;
					currentNote = sharpMarking;
					
				}
			}
		});
		addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				text.append("allowed x " + allowedX + "\nX: " + e.getX() + "\nY: " + e.getY());
				repaint();
			}
		});
		addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e){

				if(!IS_RELEASED)
				{
					if(e.getY() >= GuiHelper.getActiveStaffBeginningCoordinate() - 15 && e.getY() <= GuiHelper.getActiveStaffBeginningCoordinate() + 90)
					{
						
						if(e.getX()> allowedX + 10 && e.getX() > ((Staff.getActiveStaff() == 1) ? 160 : allowedX + 10) && !(currentNote instanceof SharpMarking))
						{
							graphics2D.setPaint(Color.BLACK);
							Integer verticalParameter = GuiHelper.verticalCoordinate(e.getY(),GuiHelper.getActiveStaffBeginningCoordinate() ,text);
							currentNote.setParameters(currentX, verticalParameter, true);
							if(!(currentNote instanceof SharpMarking))
							{
								Integer tone = SongProcessor.addNote(verticalParameter);
								if(tone<130)
								{
									isInverted = true;
								}
								else
								{
									isInverted = false;
								}
								text.append(tone.toString());
							}
							currentNote.paint(graphics2D);
							
							
							allowedX = 10 + e.getX();
							if(currentNote instanceof EighthNote)
							{
								PageController.getPages().get(pageDisplayed).getDrawnNotes().add(new EighthNote(e.getX() - 10,verticalParameter, NoteDrawing.CHECK, isInverted));
							}
							if(currentNote instanceof QuarterNote)
							{
								PageController.getPages().get(pageDisplayed).getDrawnNotes().add(new QuarterNote(e.getX() - 10, verticalParameter, NoteDrawing.CHECK, isInverted));
							}
							if(currentNote instanceof HalfNote)
							{
								PageController.getPages().get(pageDisplayed).getDrawnNotes().add(new HalfNote(e.getX() - 10, verticalParameter, NoteDrawing.CHECK, isInverted));
							}
							if(currentNote instanceof FullNote)
							{
								PageController.getPages().get(pageDisplayed).getDrawnNotes().add(new FullNote(e.getX() - 10, verticalParameter, NoteDrawing.CHECK));
							}
							
							paintLines();
							
						}
						else if(currentNote instanceof SharpMarking && e.getX()> allowedX + 10)
						{
							allowedX = 10 + e.getX();
							Integer verticalParameter = GuiHelper.verticalCoordinate(e.getY(),GuiHelper.getActiveStaffBeginningCoordinate() ,text);
							PageController.getPages().get(pageDisplayed).getDrawnNotes().add(new SharpMarking(e.getX() - 10, verticalParameter - 10));
							currentNote.setParameters(currentX, verticalParameter, true);
							currentNote.paint(graphics2D);
							if(e.getX() < 120 && Staff.getActiveStaff() == 1)
							{
								SongProcessor.makeNoteSharpGlobally(verticalParameter);
							}
							else
							{
								SongProcessor.makeNoteSharp(verticalParameter);
							}
								
							paintLines();
						}
						if(allowedX + 10 > 525 && Staff.getActiveStaff() < 3)
						{
							allowedX = 0;
							Staff.setActiveStaff(Staff.getActiveStaff() + 1);
						}
						else if(allowedX + 10 > 525 && Staff.getActiveStaff() == 3)
						{
							PageController.getPages().get(pageDisplayed).setActive(false);
							pageDisplayed += 1;
							PageController.getPages().put(pageDisplayed, new Page(pageDisplayed));
							PageController.setActivePage(pageDisplayed);
							Staff.setActiveStaff(1);
							allowedX = 80;
						}
					}
					paintLines();
					
				}
				currentNote = null;
				SongProcessor.setLENGTH(null);
				IS_RELEASED = true;
				repaint();
				
			}
		});
		
		addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseDragged(MouseEvent e){
				currentX = e.getX() - 10 ;
				currentY = e.getY() - 5;
				
				if(currentNote != null)
				{
					
					oldX = currentX;
					oldY = currentY;
					
//					graphics2D.setPaint(Color.WHITE);
//					currentNote.setParameters(oldX - 5, oldY - 2, NoteDrawing.SKIP_CHECK);
//					currentNote.paintComponent(graphics2D);
					
					paintLines();
					graphics2D.setPaint(Color.BLACK);
					currentNote.setParameters(currentX, currentY, NoteDrawing.SKIP_CHECK);
					currentNote.paintComponent(graphics2D);
					
					repaint();
				}
			}
		});		
	}
	
	public void initialize()
	{
		Staff.setActiveStaff(1);
		allowedX = 80;
		image = createImage(getSize().width, getSize().height);
		graphics2D = (Graphics2D)image.getGraphics();
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		clear();
		paintLines();
		
	}
	
	public void paintLines()
	{
		clear();
		PageController.getPages().get(pageDisplayed).paintComponent(graphics2D);
	}

	public void paintComponent(Graphics g){
		if(image == null){
			System.out.println("witdth: " + getSize().width + " height: " + getSize().height);
			image = createImage(getSize().width, getSize().height);
			graphics2D = (Graphics2D)image.getGraphics();
			graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			clear();

		}
		g.drawImage(image, 0, 0, null);
	}
	
	public void paint(Graphics g){
		super.paint(g);
	}

	public void clear()
	{
		graphics2D.setPaint(Color.white);
		graphics2D.fillRect(0, 0, getSize().width, getSize().height);
		graphics2D.setPaint(Color.black);
		repaint();
	}

	public int getAllowedX() {
		return allowedX;
	}

	public void setAllowedX(int allowedX) {
		this.allowedX = allowedX;
	}

	public int getPageDisplayed() {
		return pageDisplayed;
	}

	public void setPageDisplayed(int page) {
		this.pageDisplayed = page;
		paintLines();
	}

}