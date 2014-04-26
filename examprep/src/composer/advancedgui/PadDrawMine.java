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

import composer.data.Lengths;

class PadDrawMine extends JComponent //Popatrzec na sharp niedzialajacy na innych liniach
{
	
	private static final long serialVersionUID = 1L;
	Image image;
	Graphics2D graphics2D;
	int currentX, currentY, oldX, oldY;
	private int allowedX = 80;
	
	private boolean IS_RELEASED = true;
	
	private static List<NoteDrawing> drawnNotes = new ArrayList<NoteDrawing>();
	
	private static final EighthNote EIGHTH_NOTE = new EighthNote(GuiHelper.getFirstBoxStartingPoint() + 8, 62);
	private static final QuarterNote QUARTER_NOTE = new QuarterNote(GuiHelper.getSecondBoxStartingPoint() + 10, 62); 
	private static final HalfNote HALF_NOTE = new HalfNote(GuiHelper.getThirdBoxStartingPoint() + 10, 60);
	private static final FullNote FULL_NOTE = new FullNote(GuiHelper.getFourthBoxStartingPoint() + 10, 60);
	private static final SharpMarking SHARP_MARKING = new SharpMarking(GuiHelper.getFifthBoxStartingPoint() + 15,30);
	
	
	Staff firstStaff = new Staff(Staff.getStaffBeginningCoordinates().get(1), Staff.VIOLIN_KEY);
	//Staff firstStaff = new Staff(100, Staff.VIOLIN_KEY);
	Staff secondStaff = new Staff(Staff.getStaffBeginningCoordinates().get(2), Staff.NO_VIOLIN_KEY);
	Staff thirdStaff = new Staff(Staff.getStaffBeginningCoordinates().get(3), Staff.NO_VIOLIN_KEY);
	
	EighthNote eighthNote = new EighthNote();
	QuarterNote quarterNote = new QuarterNote(); 
	HalfNote halfNote = new HalfNote();
	FullNote fullNote = new FullNote();
	SharpMarking sharpMarking = new SharpMarking();
	NoteDrawing currentNote;
	public PadDrawMine(){
		
	}
	
	public PadDrawMine(final JTextArea text){
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
				text.append("\nX : " + e.getX() + " Y : " + e.getY() + "Staff: " + Staff.getActiveStaff() + "\n" + IS_RELEASED);
				repaint();
			}
		});
		addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e){

				if(!IS_RELEASED)
				{
					if(e.getY() >= GuiHelper.getActiveStaffBeginningCoordinate() - 15 && e.getY() <= GuiHelper.getActiveStaffBeginningCoordinate() + 90)
//						if(e.getY() >= GuiHelper.getFirstRowVerticalCoord() - 15 && e.getY() <= GuiHelper.getFifthRowVerticalCoord() + 30)
					{
						
						if(e.getX()> allowedX + 10)
						{
							graphics2D.setPaint(Color.BLACK);
//							Integer verticalParameter = GuiHelper.verticalCoordinate(e.getY(), text);
							Integer verticalParameter = GuiHelper.verticalCoordinateUp(e.getY(),GuiHelper.getActiveStaffBeginningCoordinate() ,text);
//							currentNote.setParameters(currentX, GuiHelper.verticalCoordinate(e.getY(), text));
							currentNote.setParameters(currentX, verticalParameter);
							currentNote.paint(graphics2D);
							if(!(currentNote instanceof SharpMarking))
							{
								SongProcessor.addNote(verticalParameter);
							}
							
							
							allowedX = 10 + e.getX();
							if(currentNote instanceof EighthNote)
							{
//								drawnNotes.add(new EighthNote(e.getX() - 10, GuiHelper.verticalCoordinate(e.getY(), text)));
								drawnNotes.add(new EighthNote(e.getX() - 10,verticalParameter));
							}
							if(currentNote instanceof QuarterNote)
							{
//								drawnNotes.add(new QuarterNote(e.getX() - 10, GuiHelper.verticalCoordinate(e.getY(), text)));
								drawnNotes.add(new QuarterNote(e.getX() - 10, verticalParameter));
							}
							if(currentNote instanceof HalfNote)
							{
								drawnNotes.add(new HalfNote(e.getX() - 10, verticalParameter));
//								drawnNotes.add(new HalfNote(e.getX() - 10, GuiHelper.verticalCoordinate(e.getY(), text)));
							}
							if(currentNote instanceof FullNote)
							{
								drawnNotes.add(new FullNote(e.getX() - 10, verticalParameter));
//								drawnNotes.add(new FullNote(e.getX() - 10, GuiHelper.verticalCoordinate(e.getY(), text)));
							}
							if(currentNote instanceof SharpMarking)
							{
//								drawnNotes.add(new SharpMarking(e.getX() - 10, GuiHelper.verticalCoordinate(e.getY(), text) - 10));
								drawnNotes.add(new SharpMarking(e.getX() - 10, verticalParameter - 10));
								SongProcessor.makeNoteSharp(verticalParameter);
							}
							
							paintLines();
							
						}
						else
						{
							paintLines();
						}
						if(e.getX() > 525 && Staff.getActiveStaff() < 3)
						{
							text.append("STAFF: " + Staff.getActiveStaff());
							allowedX = 0;
							Staff.setActiveStaff(Staff.getActiveStaff() + 1);
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
					
					paintLines();
					graphics2D.setPaint(Color.WHITE);
					currentNote.setParameters(oldX - 5, oldY - 2);
					currentNote.paintComponent(graphics2D);
					
					graphics2D.setPaint(Color.BLACK);
					currentNote.setParameters(currentX, currentY);
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
//		graphics2D.drawImage(GuiHelper.getImage(GuiHelper.getViolinKeyFile()), 0, GuiHelper.getFirstRowVerticalCoord() -20, 100, 100, null);
//		graphics2D.drawLine(GuiHelper.getRowHorizontalStartingPoint(), GuiHelper.getFirstRowVerticalCoord(), GuiHelper.getRowHorizontalEndingPoint(), GuiHelper.getFirstRowVerticalCoord());
//		graphics2D.drawLine(GuiHelper.getRowHorizontalStartingPoint(), GuiHelper.getSecondRowVerticalCoord(), GuiHelper.getRowHorizontalEndingPoint(), GuiHelper.getSecondRowVerticalCoord());
//		graphics2D.drawLine(GuiHelper.getRowHorizontalStartingPoint(), GuiHelper.getThirdRowVerticalCoord(), GuiHelper.getRowHorizontalEndingPoint(), GuiHelper.getThirdRowVerticalCoord());
//		graphics2D.drawLine(GuiHelper.getRowHorizontalStartingPoint(), GuiHelper.getFourthRowVerticalCoord(), GuiHelper.getRowHorizontalEndingPoint(), GuiHelper.getFourthRowVerticalCoord());
//		graphics2D.drawLine(GuiHelper.getRowHorizontalStartingPoint(), GuiHelper.getFifthRowVerticalCoord(), GuiHelper.getRowHorizontalEndingPoint(), GuiHelper.getFifthRowVerticalCoord());
		firstStaff.paintComponent(graphics2D);
		graphics2D.drawLine(GuiHelper.getRowHorizontalStartingPoint(), GuiHelper.getFifthRowVerticalCoord() + 15, GuiHelper.getRowHorizontalEndingPoint(), GuiHelper.getFifthRowVerticalCoord() + 15);
		
		
		graphics2D.drawRect(GuiHelper.getFirstBoxStartingPoint(), GuiHelper.getBoxVerticalStartingPoint(), GuiHelper.getBoxWidth(), GuiHelper.getBoxHeight());
		graphics2D.drawRect(GuiHelper.getSecondBoxStartingPoint(), GuiHelper.getBoxVerticalStartingPoint(), GuiHelper.getBoxWidth(), GuiHelper.getBoxHeight());
		graphics2D.drawRect(GuiHelper.getThirdBoxStartingPoint(), GuiHelper.getBoxVerticalStartingPoint(), GuiHelper.getBoxWidth(), GuiHelper.getBoxHeight());
		graphics2D.drawRect(GuiHelper.getFourthBoxStartingPoint(), GuiHelper.getBoxVerticalStartingPoint(), GuiHelper.getBoxWidth(), GuiHelper.getBoxHeight());
		graphics2D.drawRect(GuiHelper.getFifthBoxStartingPoint(), GuiHelper.getBoxVerticalStartingPoint(), GuiHelper.getBoxWidth(), GuiHelper.getBoxHeight());
		
		EIGHTH_NOTE.paintComponent(graphics2D);
		QUARTER_NOTE.paintComponent(graphics2D);
		HALF_NOTE.paintComponent(graphics2D);
		FULL_NOTE.paintComponent(graphics2D);
		
		SHARP_MARKING.paintComponent(graphics2D);
		
		secondStaff.paintComponent(graphics2D);
		
		thirdStaff.paintComponent(graphics2D);
		
		for(NoteDrawing nD : drawnNotes)
		{
			nD.paintComponent(graphics2D);
		}
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

	public static List<NoteDrawing> getDrawnNotes() {
		return drawnNotes;
	}

	public int getAllowedX() {
		return allowedX;
	}

	public void setAllowedX(int allowedX) {
		this.allowedX = allowedX;
	}

}