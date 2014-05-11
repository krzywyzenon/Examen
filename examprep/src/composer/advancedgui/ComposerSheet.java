package composer.advancedgui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JTextArea;

import composer.advancedgui.shapes.EighthNote;
import composer.advancedgui.shapes.FullNote;
import composer.advancedgui.shapes.HalfNote;
import composer.advancedgui.shapes.NoteDrawing;
import composer.advancedgui.shapes.QuarterNote;
import composer.advancedgui.shapes.SharpMarking;
import composer.data.Lengths;
import composer.data.MidiDataExtractor;
import composer.data.SoundDrawRelations;

class ComposerSheet extends JComponent 
{
	private static final long serialVersionUID = 1L;
	Image image;
	Graphics2D graphics2D;
	int currentX, currentY, oldX, oldY;
	private int allowedX = 80;
	private Integer originalVerticalPosition = null;
	
	private int pageDisplayed = 1;
	
	private boolean editingMode = false;
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
				if(GuiHelper.isCursorWithinLimits(e.getX(), e.getY(), GuiHelper.getFirstBoxStartingPoint(), GuiHelper.getBoxVerticalStartingPoint(), GuiHelper.ADD))
				{
					currentNote = eighthNote;
				}
				else if(GuiHelper.isCursorWithinLimits(e.getX(), e.getY(), GuiHelper.getSecondBoxStartingPoint(), GuiHelper.getBoxVerticalStartingPoint(), GuiHelper.ADD))
				{
					currentNote = quarterNote;
				}
				else if(GuiHelper.isCursorWithinLimits(e.getX(), e.getY(), GuiHelper.getThirdBoxStartingPoint(), GuiHelper.getBoxVerticalStartingPoint(), GuiHelper.ADD))
				{
					currentNote = halfNote;	
				}
				else if(GuiHelper.isCursorWithinLimits(e.getX(), e.getY(),  GuiHelper.getFourthBoxStartingPoint(), GuiHelper.getBoxVerticalStartingPoint(), GuiHelper.ADD))
				{
					currentNote = fullNote;
				}
				else if(GuiHelper.isCursorWithinLimits(e.getX(), e.getY(), GuiHelper.getFifthBoxStartingPoint(), GuiHelper.getBoxVerticalStartingPoint(), GuiHelper.ADD))
				{
					currentNote = sharpMarking;
					
				}
				else
				{
					oldX = e.getX();
					oldY = e.getY();
					Page page = PageController.getPages().get(pageDisplayed);
					List<NoteDrawing> list = page.getDrawnNotes();
					for(NoteDrawing nd : list)
					{
						if(GuiHelper.isCursorWithinLimits(oldX, oldY, nd.getBallFromX(), nd.getBallFromY(), GuiHelper.EDIT))
						{
							System.out.println("Hello World");
							editingMode = true;
							currentNote = nd;
							originalVerticalPosition = nd.getBallFromY();
						}
					}
				}
			}
		});
		addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				text.append("allowed x " + allowedX + "\nX: " + e.getX() + "\nY: " + e.getY());
				System.out.println("x: " + e.getX());
				repaint();
			}
		});
		addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e){

					if(!editingMode)
					{
						
						if(e.getY() >= GuiHelper.getActiveStaffBeginningCoordinate() - 15 && e.getY() <= GuiHelper.getActiveStaffBeginningCoordinate() + 90)
						{
							
							if(e.getX()> allowedX + 10 && e.getX() > ((Staff.getActiveStaff() == 1) ? 160 : allowedX + 10) && !(currentNote instanceof SharpMarking))
							{
								graphics2D.setPaint(Color.BLACK);
								Integer verticalParameter = GuiHelper.verticalCoordinate(e.getY(),GuiHelper.getActiveStaffBeginningCoordinate() ,text);
								currentNote.setParameters(currentX, verticalParameter, true);
								Integer [] toneData = null;
								
								currentNote.paint(graphics2D);
								
								allowedX = 10 + e.getX();
								if(currentNote instanceof EighthNote)
								{
									toneData = SongProcessor.addNote(verticalParameter, Lengths.EIGHT);
									isInverted = setInverted(toneData[0]);
									EighthNote noteToAdd = new EighthNote(e.getX() - 10,verticalParameter, NoteDrawing.CHECK, isInverted);
									addingNoteDrawing(noteToAdd, toneData[1]);
								}
								if(currentNote instanceof QuarterNote)
								{
									toneData = SongProcessor.addNote(verticalParameter, Lengths.QUARTER);
									isInverted = setInverted(toneData[0]);
									QuarterNote noteToAdd = new QuarterNote(e.getX() - 10, verticalParameter, NoteDrawing.CHECK, isInverted);
									addingNoteDrawing(noteToAdd, toneData[1]);
								}
								if(currentNote instanceof HalfNote)
								{
									toneData = SongProcessor.addNote(verticalParameter, Lengths.HALF);
									isInverted = setInverted(toneData[0]);
									HalfNote noteToAdd = new HalfNote(e.getX() - 10, verticalParameter, NoteDrawing.CHECK, isInverted);
									addingNoteDrawing(noteToAdd, toneData[1]);
								}
								if(currentNote instanceof FullNote)
								{
									toneData = SongProcessor.addNote(verticalParameter, Lengths.WHOLE);
									isInverted = setInverted(toneData[0]);
									FullNote noteToAdd = new FullNote(e.getX() - 10, verticalParameter, NoteDrawing.CHECK);
									addingNoteDrawing(noteToAdd, toneData[1]);
								}
								System.out.println("allowed x: " + allowedX);
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
					}
					else
					{
						//Editing existing note
						if(e.getY() >= GuiHelper.getActiveStaffBeginningCoordinate() - 15 && e.getY() <= GuiHelper.getActiveStaffBeginningCoordinate() + 90)
						{
							if(!(currentNote instanceof SharpMarking))
							{
								graphics2D.setPaint(Color.BLACK);
								Integer verticalParameter = GuiHelper.verticalCoordinate(e.getY(),GuiHelper.getActiveStaffBeginningCoordinate() ,text);
								Integer index = SoundDrawRelations.getDrawingsAndSoundsRelations().get(currentNote);
								Object[] data = SongProcessor.getMidiTone(verticalParameter);
								Integer toneValue = (Integer) data[1];
								isInverted = setInverted(toneValue);
								currentNote.setParameters(currentNote.getBallFromX(), verticalParameter, true);
								currentNote.setInverted(isInverted);
								currentNote.paint(graphics2D);
								MidiDataExtractor tone = (MidiDataExtractor) data[0];
								SongProcessor.getSong().get(index).setTone(tone);
							}
						}
						else
						{
							
							currentNote.setParameters(currentNote.getBallFromX(), originalVerticalPosition, true);
							currentNote.paint(graphics2D);
							originalVerticalPosition = null;
						}
					}
					paintLines();
				currentNote = null;
				editingMode = false;
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
					if(!editingMode)
					{
						graphics2D.setPaint(Color.BLACK);
						currentNote.setParameters(currentX, currentY, NoteDrawing.SKIP_CHECK);
						currentNote.paintComponent(graphics2D);
					}
					else
					{
						graphics2D.setPaint(Color.BLACK);
						currentNote.setParameters(currentNote.getBallFromX(), currentY, NoteDrawing.SKIP_CHECK);
						currentNote.paintComponent(graphics2D);
					}
						
				}
			}
		});		
	}
	
	public void initialize()
	{
		Staff.setActiveStaff(1);
		allowedX = 80;
//		image = createImage(getSize().width, getSize().height);
		image = createImage(559, 531);
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
	
	public boolean setInverted(Integer value)
	{
		if(value<130)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void addingNoteDrawing(NoteDrawing drawing, Integer index){
		
		PageController.getPages().get(pageDisplayed).getDrawnNotes().add(drawing);
		SoundDrawRelations.getDrawingsAndSoundsRelations().put(drawing, index);
	}

}