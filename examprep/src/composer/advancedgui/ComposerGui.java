package composer.advancedgui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import com.google.common.collect.ImmutableMap;
import composer.advancedgui.shapes.NoteDrawing;
import composer.controller.PageController;
import composer.controller.PlayController;
import composer.controller.SaveAndLoadController;
import composer.controller.SongProcessor;
import composer.controller.State;
import composer.data.Instruments;
import composer.data.SoundDrawRelations;

public class ComposerGui implements ActionListener
{
	private static Map<Object, Integer> SOURCE = null;
	
	private static final int LOAD = 1;
	private static final int SAVE = 2;
	private static final int QUIT = 3;
	private static final int CLEAR = 4;
	private static final int PLAY = 5;
	private static final int NEXT = 6;
	private static final int PREVIOUS = 7;
	private static final int DELETE_LAST = 8;
	private static final int SET_TITLE = 9;
	private static final int PIANO = 10;
	private static final int ORGANS = 11;
	private static final int HARP = 12;
	private static final int TUBULAR_BELL = 13;
	private static final int VIOLIN = 14;
	private static final int CELLO = 15;
	private static final int CONTRABAS = 16;

	private static Instruments instrument = Instruments.PIANO;
	
	private ComposerSheet composerSheet;
	
	private ImageIcon playIcon, nextIcon, previousIcon, clearIcon,deleteNoteIcon, titleIcon;
	private Image background, menuBackground, controlPanelBackground;
	
	private JFrame frame;
	private JTextField title;
	
	private JLabel titleLabel;
	
	private BackgroundPanel mainPanel, controlPanel;
	
	private Container content;
	
	private JButton clearButton, nextPage, previousPage, playButton, deleteLastButton, setTitleButton;
	
	private JMenuItem loadItem, saveItem, quitItem;
	
	private JRadioButtonMenuItem pianoItem, organItem = new JRadioButtonMenuItem("Organs"), harpItem = new JRadioButtonMenuItem("Harp"), 
			tubularBellItem = new JRadioButtonMenuItem("Tubular Bell"), violinItem = new JRadioButtonMenuItem("Violin"), celloItem = new JRadioButtonMenuItem("Cello"),
			contrabasItem = new JRadioButtonMenuItem("Contrabas");
	
	private ButtonGroup instrumentGroup;
	
	private MyMenuBar menuBar;
	private JMenu fileMenu, instrumentsMenu;
	
	private JFileChooser fc;
	
	private GridBagConstraints gc;

	private final JRadioButtonMenuItem[] instrumentList = {organItem, harpItem, tubularBellItem, violinItem, celloItem, contrabasItem};

	private JButton[] buttons ={clearButton = new JButton(), nextPage = new JButton(), previousPage = new JButton(), playButton = new JButton(), deleteLastButton = new JButton()
	,setTitleButton = new JButton()};
	
	private ImageIcon[] icons = {clearIcon = new ImageIcon(GuiHelper.getImage(GuiHelper.getClearKeyFile())), nextIcon = new ImageIcon(GuiHelper.getImage(GuiHelper.getNextPageKeyFile())),
			previousIcon = new ImageIcon(GuiHelper.getImage(GuiHelper.getPreviousPageKeyFile())), playIcon = new ImageIcon(GuiHelper.getImage(GuiHelper.getPlayKeyFile())),
			deleteNoteIcon = new ImageIcon(GuiHelper.getImage(GuiHelper.getDeleteLastNoteFile())), titleIcon = new ImageIcon(GuiHelper.getImage(GuiHelper.getTitleButtonFile()))};
	
	private String[] tooltips = {"Clear this song", "Next Page", "Previous page", "Play this song", "Delete the last note", "Name your song"};
	
	private JMenuItem[] fileMenuItems = {loadItem = new JMenuItem("Load song"), saveItem = new JMenuItem("Save song"), quitItem = new JMenuItem("Quit")};
	
	public ComposerGui() {
		//initializing variables and configuring gui elements
		gc = new GridBagConstraints();
		background = GuiHelper.getImage(GuiHelper.getMainBackgroundFile());
		menuBackground = GuiHelper.getImage(GuiHelper.getMenuBackgroundFile());
		controlPanelBackground = GuiHelper.getImage(GuiHelper.getPanelBackgroundFile());
		frame = new JFrame("Composer");
		mainPanel = new BackgroundPanel(background);
		mainPanel.setLayout(new BorderLayout());
		title = new JTextField(10);
		title.setPreferredSize(new Dimension(140, 30));
		title.setMinimumSize(new Dimension(140, 30));
		title.setBackground(new Color(157,75,35));
		title.setForeground(Color.ORANGE);
		titleLabel = new JLabel();
		titleLabel.setFont(new Font("Serif", Font.BOLD, 18));
		titleLabel.setForeground(Color.BLACK);
		titleLabel.setText("Title:");
		
		instrumentGroup = new ButtonGroup();
		instrumentsMenu = new JMenu("Instruments");
		instrumentsMenu.setForeground(Color.ORANGE);
		fc = new JFileChooser();
		fc.setCurrentDirectory(GuiHelper.getSavesDirectory());
		
		for(JMenuItem menuItem : fileMenuItems)
		{
			menuItem.setBackground(new Color(157,75,35));
			menuItem.setForeground(Color.ORANGE);
			menuItem.addActionListener(this);
		}
		
		for(JRadioButtonMenuItem button : instrumentList)
		{
			button.addActionListener(this);
			button.setBackground(new Color(157,75,35));
			button.setForeground(Color.ORANGE);
			instrumentGroup.add(button);
			instrumentsMenu.add(button);
		}
		
		for(JButton button : buttons )
		{
			button.setIcon(icons[Arrays.asList(buttons).indexOf(button)]);
			button.setBorder(null);
			button.setToolTipText(tooltips[Arrays.asList(buttons).indexOf(button)]);
			button.addActionListener(this);
		}
		
		composerSheet  = new ComposerSheet();
		
		pianoItem = new JRadioButtonMenuItem("Piano");
		pianoItem.setSelected(true);
		pianoItem.addActionListener(this);
		pianoItem.setBackground(new Color(157,75,35));
		pianoItem.setForeground(Color.ORANGE);
		instrumentGroup.add(pianoItem);
		instrumentsMenu.add(pianoItem);

		SOURCE = ImmutableMap.<Object, Integer>builder()
					.put(loadItem, 1)
					.put(saveItem, 2)
					.put(quitItem, 3)
					.put(clearButton, 4)
					.put(playButton, 5)
					.put(nextPage, 6)
					.put(previousPage, 7)
					.put(deleteLastButton, 8)
					.put(setTitleButton, 9)
					.put(pianoItem, 10)
					.put(organItem, 11)
					.put(harpItem, 12)
					.put(tubularBellItem, 13)
					.put(violinItem, 14)
					.put(celloItem, 15)
					.put(contrabasItem, 16)
					.build();
		
		content = frame.getContentPane();
		content.setLayout(new BorderLayout());
		
		menuBar = new MyMenuBar(menuBackground);
		menuBar.setBorder(null);
		fileMenu = new JMenu("File");
		fileMenu.setForeground(Color.ORANGE);
		fileMenu.add(saveItem);
		fileMenu.add(loadItem);
		fileMenu.add(new JSeparator());
		fileMenu.add(quitItem);
		
		menuBar.add(fileMenu);
		menuBar.add(instrumentsMenu);
		frame.setJMenuBar(menuBar);
		
		mainPanel.add(composerSheet, BorderLayout.CENTER);
		
		controlPanel = new BackgroundPanel(controlPanelBackground);
		
		controlPanel.setLayout(new GridBagLayout());
		gc.anchor = GridBagConstraints.WEST;
		gc.weighty = 0.05;
		gc.weightx = 1;
		gc.gridx = 0;
		gc.gridy = 0;
		controlPanel.add(playButton, gc);
		gc.gridx = 0;
		gc.gridy = 1;
		controlPanel.add(previousPage, gc);
		
		gc.gridx = 1;
		JLabel pageLabel = new JLabel("PAGE");
		pageLabel.setFont(new Font("Serif", Font.BOLD, 24));
		pageLabel.setForeground(Color.BLACK);
		pageLabel.setMinimumSize(new Dimension(120, 40));
		gc.weightx = 0.01;
		controlPanel.add(pageLabel, gc);
		gc.weightx =1;
		
		gc.gridx = 2;
		gc.anchor = GridBagConstraints.CENTER;
		controlPanel.add(nextPage, gc);
		
		gc.anchor = GridBagConstraints.WEST;
		gc.weightx = 1;
		gc.gridy = 2;
		gc.gridx = 0;
		gc.gridwidth = 2;
		controlPanel.add(clearButton, gc);
		gc.gridwidth = 1;
		
		gc.gridx = 2;
		controlPanel.add(deleteLastButton, gc);
		gc.anchor = GridBagConstraints.WEST;
		gc.weightx = 0;
		gc.gridy = 3;
		gc.gridx = 0;
		gc.gridwidth = 2;
		controlPanel.add(title, gc);
		gc.gridwidth = 1;
		gc.gridx = 2;
		controlPanel.add(setTitleButton, gc);
		
		gc.gridy = 4;
		gc.weighty = 1;
		gc.gridx = 0;
		gc.gridwidth = 3;
		controlPanel.add(titleLabel, gc);
		
		mainPanel.add(controlPanel, BorderLayout.WEST);
		
		mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 5));
		
		content.add(mainPanel);
		frame.setSize(806, 649);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		composerSheet.initialize();
		frame.repaint();

	}

	//Action Listener
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		int source = SOURCE.get(e.getSource());
		switch(source)
		{
			case QUIT:
			{
				System.exit(0);
				break;
			}
			case PLAY:
			{
				PlayController p = new PlayController(SongProcessor.getSong(), instrument);
				Thread t = new Thread(p);
				t.start();
				break;
			}
			case NEXT:
			{
				if(PageController.getPages().containsKey(composerSheet.getPageDisplayed() + 1))
				{
					composerSheet.setPageDisplayed(composerSheet.getPageDisplayed() + 1);
				}
				break;
			}
			case PREVIOUS:
			{
				if(PageController.getPages().containsKey(composerSheet.getPageDisplayed() - 1))
				{
					composerSheet.setPageDisplayed(composerSheet.getPageDisplayed() - 1);
				}
				break;
			}
			case CLEAR:
			{
				PageController.clearNotes();
				composerSheet.setPageDisplayed(1);
				composerSheet.setAllowedX(80);
				SongProcessor.getSong().clear();
				SongProcessor.cleanAllSharps();
				SongProcessor.cleanAllFlats();
				SoundDrawRelations.resetRelations();
				title.setText("");
				titleLabel.setText("Title:");
				composerSheet.initialize();
				break;
			}
			case LOAD:
			{
				int returnVal = fc.showOpenDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION)
				{
					File file = fc.getSelectedFile();
					State state;
					try {
						state = SaveAndLoadController.loadSong(file);
						SongProcessor.setSong(state.getSong());
						PageController.setPages(state.getPages());
						SoundDrawRelations.setDrawingsAndSoundsRelations(state.getRelations());
						titleLabel.setText("Title: " + state.getTitle());
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					composerSheet.paintLines();
				}
				break;
			}
			case SAVE:
			{
				int returnVal = fc.showSaveDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION)
				{
					File file = fc.getSelectedFile();
					String name = (title.getText() != null)  ? title.getText() : "";
					State state = new State(SongProcessor.getSong(), PageController.getPages(), SoundDrawRelations.getDrawingsAndSoundsRelations(), name);
					try {
						SaveAndLoadController.saveSong(state, file);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				break;
			}
			case DELETE_LAST:
			{
				Page page = PageController.getPages().get(PageController.getActivePage());
				int size = page.getDrawnNotes().size();
				NoteDrawing nD = page.getDrawnNotes().get(size -1);
				int index = SoundDrawRelations.getDrawingsAndSoundsRelations().get(nD);
				SoundDrawRelations.getDrawingsAndSoundsRelations().remove(nD);
				SongProcessor.getSong().remove(index);
				page.getDrawnNotes().remove(size -1);
				composerSheet.setAllowedX(composerSheet.getAllowedX() - 45);
				composerSheet.paintLines();
				break;
			}
			case SET_TITLE:
			{
				titleLabel.setText("Title: " + title.getText());
				break;
			}
			
			case PIANO:
			{
				instrument = Instruments.PIANO;
				break;
			}
			case ORGANS:
			{
				instrument = Instruments.ORGANS;
				break;
			}
			case HARP:
			{
				instrument = Instruments.HARPSICHORD;
				break;
			}
			case TUBULAR_BELL:
			{
				instrument = Instruments.TUBULAR_BELL;
				break;
			}
			case VIOLIN:
			{
				instrument = Instruments.VIOLIN;
				break;
			}
			case CELLO:
			{
				instrument = Instruments.CELLO;
				break;
			}
			case CONTRABAS:
			{
				instrument = Instruments.CONTRABAS;
				break;
			}
		}
		
	}
	public ComposerSheet getComposerSheet()
	{
		return this.composerSheet;
	}
	
	public static void main(String[] args) {
		ComposerGui gui = new ComposerGui();
		PlayController.setComposerSheet(gui.getComposerSheet());
	}

}









