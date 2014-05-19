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

import composer.advancedgui.shapes.EighthNote;
import composer.advancedgui.shapes.FlatMarking;
import composer.advancedgui.shapes.FullNote;
import composer.advancedgui.shapes.HalfNote;
import composer.advancedgui.shapes.NoteDrawing;
import composer.advancedgui.shapes.QuarterNote;
import composer.advancedgui.shapes.RestMarking;
import composer.advancedgui.shapes.SharpMarking;
import composer.controller.PageController;
import composer.controller.SongProcessor;
import composer.data.Lengths;
import composer.data.MidiDataExtractor;
import composer.data.SoundDrawRelations;

/**
 * 
 * @author Tomek
 * This is the sheet upon which every staff and note are being drawn
 */
public class ComposerSheet extends JComponent 
{
	private static final long serialVersionUID = 1L;
	private Image image;
	private static Graphics2D graphics2D;
	private int currentX, currentY;
	private int allowedX = 80;
	private Integer originalVerticalPosition = null;
	
	private int pageDisplayed = 1;
	
	private boolean editingMode = false;
	private boolean isInverted = false;
	private NoteDrawing currentNote;
	
	private Integer activeStaff = 1;
	
	public ComposerSheet(){
		super();

		setDoubleBuffered(false);

		/**
		 * While the mouse is pressed, the listener checks for the cursor position. If it is within the shapes which can be drawn
		 * to the staff, it sets the dynamic object's (currentNote) value to the chosen note. Otherwise if the cursor is positioned
		 * on the note which has already been drawn, the listener assumes that the user chooses to edit the chosen note's position
		 * and sets working mode to editing.
		 */
		addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				currentX = e.getX();
				currentY = e.getY();
				if(GuiHelper.isCursorWithinLimits(e.getX(), e.getY(), GuiHelper.getFirstBoxStartingPoint(), GuiHelper.getBoxVerticalStartingPoint(), GuiHelper.ADD))
				{
					currentNote = new EighthNote();
				}
				else if(GuiHelper.isCursorWithinLimits(e.getX(), e.getY(), GuiHelper.getSecondBoxStartingPoint(), GuiHelper.getBoxVerticalStartingPoint(), GuiHelper.ADD))
				{
					currentNote = new QuarterNote();
				}
				else if(GuiHelper.isCursorWithinLimits(e.getX(), e.getY(), GuiHelper.getThirdBoxStartingPoint(), GuiHelper.getBoxVerticalStartingPoint(), GuiHelper.ADD))
				{
					currentNote = new HalfNote();	
				}
				else if(GuiHelper.isCursorWithinLimits(e.getX(), e.getY(),  GuiHelper.getFourthBoxStartingPoint(), GuiHelper.getBoxVerticalStartingPoint(), GuiHelper.ADD))
				{
					currentNote = new FullNote();
				}
				else if(GuiHelper.isCursorWithinLimits(e.getX(), e.getY(), GuiHelper.getFifthBoxStartingPoint(), GuiHelper.getBoxVerticalStartingPoint(), GuiHelper.ADD))
				{
					currentNote = new SharpMarking();
					
				}
				else if(GuiHelper.isCursorWithinLimits(e.getX(), e.getY(), GuiHelper.getSixthBoxStartingPoint(), GuiHelper.getBoxVerticalStartingPoint(), GuiHelper.ADD))
				{
					currentNote = new FlatMarking();
					
				}
				else if(GuiHelper.isCursorWithinLimits(e.getX(), e.getY(), GuiHelper.getSeventhBoxStartingPoint(), GuiHelper.getBoxVerticalStartingPoint(), GuiHelper.ADD))
				{
					currentNote = new RestMarking();
					
				}
				else
				{
					currentX = e.getX();
					currentY = e.getY();
					Page page = PageController.getPages().get(pageDisplayed);
					List<NoteDrawing> list = page.getDrawnNotes();
					for(NoteDrawing noteDrawing : list)
					{
						if(GuiHelper.isCursorWithinLimits(currentX, currentY, noteDrawing.getBallFromX(), noteDrawing.getBallFromY(), GuiHelper.EDIT))
						{
							editingMode = true;
							currentNote = noteDrawing;
							originalVerticalPosition = noteDrawing.getBallFromY();
							activeStaff = Staff.getActiveStaff();
							Staff.setActiveStaff(noteDrawing.staffForThisNote());
						}
					}
				}
			}
		});
		addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				System.out.println("x: " + e.getX());
				System.out.println("y: " + e.getY());
				repaint();
			}
		});
		
		/**
		 * Upon releasing the mouse button, the listener checks for the mode - if the mode is normal, the listener adjusts note's
		 * position, paints it on the staff and adds a new sound to the song. While editing mode is on, the listener adjusts note's
		 * position, paints it on the staff and modifies corresponding sound in the song.
		 */
		addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e){

					if(!editingMode && currentNote != null)
					{
						
						if(e.getY() >= GuiHelper.getActiveStaffBeginningCoordinate() - 15 && e.getY() <= GuiHelper.getActiveStaffBeginningCoordinate() + 90)
						{
							
							if(e.getX()> allowedX + 10 && e.getX() > ((Staff.getActiveStaff() == 1) ? 160 : allowedX + 10) && !(currentNote instanceof SharpMarking || currentNote instanceof FlatMarking))
							{
								graphics2D.setPaint(Color.BLACK);
								Integer verticalCoordinate = GuiHelper.countVerticalCoordinate(e.getY(),GuiHelper.getActiveStaffBeginningCoordinate());
								System.out.println(verticalCoordinate);
								System.out.println(currentX);
								currentNote.setParameters(currentX, verticalCoordinate, true);
								Integer [] toneData = null;
								
								currentNote.paint(graphics2D);
								
								allowedX = 10 + e.getX();
								if(currentNote instanceof EighthNote)
								{
									toneData = SongProcessor.addNote(verticalCoordinate, Lengths.EIGHT, false);
									isInverted = determineInverted(toneData[0]);
									EighthNote noteToAdd = new EighthNote(e.getX() - 10,verticalCoordinate, NoteDrawing.CHECK, isInverted);
									addingNoteDrawing(noteToAdd, toneData[1]);
								}
								if(currentNote instanceof QuarterNote)
								{
									toneData = SongProcessor.addNote(verticalCoordinate, Lengths.QUARTER, false);
									isInverted = determineInverted(toneData[0]);
									QuarterNote noteToAdd = new QuarterNote(e.getX() - 10, verticalCoordinate, NoteDrawing.CHECK, isInverted);
									addingNoteDrawing(noteToAdd, toneData[1]);
								}
								if(currentNote instanceof HalfNote)
								{
									toneData = SongProcessor.addNote(verticalCoordinate, Lengths.HALF, false);
									isInverted = determineInverted(toneData[0]);
									HalfNote noteToAdd = new HalfNote(e.getX() - 10, verticalCoordinate, NoteDrawing.CHECK, isInverted);
									addingNoteDrawing(noteToAdd, toneData[1]);
								}
								if(currentNote instanceof FullNote)
								{
									toneData = SongProcessor.addNote(verticalCoordinate, Lengths.WHOLE, false);
									isInverted = determineInverted(toneData[0]);
									FullNote noteToAdd = new FullNote(e.getX() - 10, verticalCoordinate, NoteDrawing.CHECK);
									addingNoteDrawing(noteToAdd, toneData[1]);
								}
								if(currentNote instanceof RestMarking)
								{
									toneData = SongProcessor.addNote(verticalCoordinate, Lengths.REST, true);
									RestMarking noteToAdd = new RestMarking(e.getX() - 10, Staff.getStaffVerticalBeginningCoordinates().get(Staff.getActiveStaff()));
									addingNoteDrawing(noteToAdd, toneData[1]);
								}
								paintLines();
								
							}
							else if((currentNote instanceof SharpMarking || currentNote instanceof FlatMarking) && e.getX()> allowedX + 10)
							{
								allowedX = 10 + e.getX();
								Integer verticalCoordinate = GuiHelper.countVerticalCoordinate(e.getY(),GuiHelper.getActiveStaffBeginningCoordinate());
								if(currentNote instanceof SharpMarking)
								{
									PageController.getPages().get(pageDisplayed).getDrawnNotes().add(new SharpMarking(e.getX() - 10, verticalCoordinate - 10));
								}
								else
								{
									PageController.getPages().get(pageDisplayed).getDrawnNotes().add(new FlatMarking(e.getX() -20, verticalCoordinate -35));
								}
								currentNote.setParameters(currentX, verticalCoordinate, true);
								currentNote.paint(graphics2D);
								if(e.getX() < 140 && Staff.getActiveStaff() == 1)
								{
									if(currentNote instanceof SharpMarking)
									{
										SongProcessor.makeNoteSharpGlobally(verticalCoordinate);
									}
									else
									{
										SongProcessor.makeNoteFlatGlobally(verticalCoordinate);
									}
								}
								else
								{
									if(currentNote instanceof SharpMarking)
									{
										SongProcessor.makeNoteLocallySharp(verticalCoordinate);
									}
									else
									{
										SongProcessor.makeNoteLocallyFlat(verticalCoordinate);
									}
								}
								
								paintLines();
							}
							if(allowedX + 10 > 525 && Staff.getActiveStaff() < 3)
							{
								Staff.setActiveStaff(Staff.getActiveStaff() + 1);
								allowedX = (Page.getStaves().get(Staff.getActiveStaff()).isViolinKey()) ? 80 : 0;
							}
							else if(allowedX + 10 > 525 && Staff.getActiveStaff() == 3)
							{
								NoteDrawing changer = PageController.getPages().get(pageDisplayed).getDrawnNotes().get((PageController.getPages().get(pageDisplayed).getDrawnNotes().size() - 1));
								changer.setPageChanger(true);
								PageController.getPages().get(pageDisplayed).setActive(false);
								pageDisplayed += 1;
								PageController.getPages().put(pageDisplayed, new Page(pageDisplayed));
								PageController.setActivePage(pageDisplayed);
								Staff.setActiveStaff(1);
								allowedX = (Page.getStaves().get(Staff.getActiveStaff()).isViolinKey()) ? 80 : 0;
							}
						}
					}
					else if(currentNote != null)
					{
						//Actions taken in editing mode. If the position, which user tries to move note to is invalid, the note is 
						//put back in its original position and the sound is not changed. IMPORTANT: it is only possible to change
						//the vertical position of the note - the horizontal position always remains the same.
						if(e.getY() >= GuiHelper.getActiveStaffBeginningCoordinate() - 15 && e.getY() <= GuiHelper.getActiveStaffBeginningCoordinate() + 90)
						{
							if(!(currentNote instanceof SharpMarking) && currentNote != null)
							{
								graphics2D.setPaint(Color.BLACK);
								Integer verticalCoordinate = GuiHelper.countVerticalCoordinate(e.getY(),GuiHelper.getActiveStaffBeginningCoordinate());
								Integer noteIndex = SoundDrawRelations.getDrawingsAndSoundsRelations().get(currentNote);
								Object[] data = SongProcessor.getMidiTone(verticalCoordinate);
								Integer noteVerticalCoord = (Integer) data[1];
								isInverted = determineInverted(noteVerticalCoord);
								currentNote.setParameters(currentNote.getBallFromX(), verticalCoordinate, true);
								currentNote.setInverted(isInverted);
								currentNote.paint(graphics2D);
								MidiDataExtractor tone = (MidiDataExtractor) data[0];
								SongProcessor.getSong().get(noteIndex).setTone(tone);
							}
						}
						else
						{
							if(currentNote!=null)
							{
								currentNote.setParameters(currentNote.getBallFromX(), originalVerticalPosition, true);
								currentNote.paint(graphics2D);
							}
							originalVerticalPosition = null;
						}
						Staff.setActiveStaff(activeStaff);
						activeStaff = null;
					}
				paintLines();
				currentNote = null;
				editingMode = false;
				repaint();
				
			}
		});
		
		/**
		 * In normal mode the listener paints the note which the user is dragging, at the cursor position. In editing mode only
		 * vertical position changes
		 */
		addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseDragged(MouseEvent e){
				currentX = e.getX() - 10 ;
				currentY = e.getY() - 5;
				
				if(currentNote != null)
				{
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
				e.getComponent().repaint();
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
		repaint();
	}
	
	/**
	 * As some notes at the staff are painted inverted, this method checks if such invertion is necessary
	 * @param verticalPosition - the vertical coordinate of the painted note
	 * @return
	 */
	public boolean determineInverted(Integer verticalPosition)
	{
		return verticalPosition<130;
	}
	
	/**
	 * The method saves the current note's object in the List of the notes which are to be drawn on screen. Then it creates a connection
	 * between the note drawing and the corresponding sound.
	 * @param drawing - the note which user wishes to draw
	 * @param index - index of the sound object, in the List, which corresponds to the drawn note.
	 */
	public void addingNoteDrawing(NoteDrawing drawing, Integer index){
		
		PageController.getPages().get(pageDisplayed).getDrawnNotes().add(drawing);
		SoundDrawRelations.getDrawingsAndSoundsRelations().put(drawing, index);
	}
	
	public void paintNote(NoteDrawing noteDrawing, Color color)
	{
		PageController.getPages().get(pageDisplayed).paintComponent(graphics2D);
		Graphics2D g2D = (Graphics2D) graphics2D.create();
		g2D.setColor(color);
		noteDrawing.paintComponent(g2D);
		repaint();
	}

}