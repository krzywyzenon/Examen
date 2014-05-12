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
import java.util.List;
import java.util.Map;

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
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import com.google.common.collect.ImmutableMap;
import composer.advancedgui.shapes.NoteDrawing;
import composer.controller.Player;
import composer.controller.SaveAndLoad;
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

	private static Instruments instrument = Instruments.PIANO;
	private ComposerSheet composerSheet;
	
	private ImageIcon playIcon;
	private ImageIcon nextIcon;
	private ImageIcon previousIcon;
	private ImageIcon clearIcon;
	private ImageIcon deleteNoteIcon;
	private ImageIcon titleIcon;
	
	private JTextArea text;
	private JFrame frame;
	private JTextField title;
	
	JLabel titleLabel;
	
	private BackgroundPanel mainPanel;
	
	private Container content;
	
	private JButton clearButton;
	private JButton nextPage;
	private JButton previousPage;
	private JButton playButton;
	private JButton deleteLastButton;
	private JButton setTitleButton;
	private JMenuItem loadItem;
	private JMenuItem saveItem;
	private JMenuItem quitItem;
	private JRadioButtonMenuItem pianoItem, organItem = new JRadioButtonMenuItem("Organs"), harpItem = new JRadioButtonMenuItem("Harp"), 
			tubularBellItem = new JRadioButtonMenuItem("Tubular Bell"), violinItem = new JRadioButtonMenuItem("Violin") ;
	private final JRadioButtonMenuItem[] instrumentList = {organItem, harpItem, tubularBellItem, violinItem};
	private ButtonGroup instrumentGroup;
	
	private MyMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu instrumentsMenu;
	
	private BackgroundPanel panel;
	
	private JFileChooser fc;
	
	private GridBagConstraints gc;
	
	public ComposerGui() {
		
		gc = new GridBagConstraints();
		playIcon = new ImageIcon(GuiHelper.getImage(GuiHelper.getPlayKeyFile()));
		nextIcon = new ImageIcon(GuiHelper.getImage(GuiHelper.getNextPageKeyFile()));
		previousIcon = new ImageIcon(GuiHelper.getImage(GuiHelper.getPreviousPageKeyFile()));
		clearIcon = new ImageIcon(GuiHelper.getImage(GuiHelper.getClearKeyFile()));
		deleteNoteIcon = new ImageIcon(GuiHelper.getImage(GuiHelper.getDeleteLastNoteFile()));
		titleIcon = new ImageIcon(GuiHelper.getImage(GuiHelper.getTitleButtonFile()));
		Image background = GuiHelper.getImage(GuiHelper.getMainBackgroundFile());
		frame = new JFrame("Composer");
		text = new JTextArea();
		text.setPreferredSize(new Dimension(210,1168));
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
		
		composerSheet  = new ComposerSheet(text);
		clearButton = new JButton();
		nextPage = new JButton();
		previousPage = new JButton();
		playButton = new JButton();
		deleteLastButton = new JButton();
		setTitleButton = new JButton();

		loadItem = new JMenuItem("Load song");
		loadItem.setBackground(new Color(157,75,35));
		loadItem.setForeground(Color.ORANGE);
		saveItem = new JMenuItem("Save song");
		saveItem.setBackground(new Color(157,75,35));
		saveItem.setForeground(Color.ORANGE);
		quitItem = new JMenuItem("Quit");
		quitItem.setBackground(new Color(157,75,35));
		quitItem.setForeground(Color.ORANGE);
		
		instrumentGroup = new ButtonGroup();
		instrumentsMenu = new JMenu("Instruments");
		instrumentsMenu.setForeground(Color.ORANGE);
		
		pianoItem = new JRadioButtonMenuItem("Piano");
		pianoItem.setSelected(true);
		pianoItem.addActionListener(this);
		pianoItem.setBackground(new Color(157,75,35));
		pianoItem.setForeground(Color.ORANGE);
		instrumentGroup.add(pianoItem);
		instrumentsMenu.add(pianoItem);
		
		for(JRadioButtonMenuItem button : instrumentList)
		{
			button.addActionListener(this);
			button.setBackground(new Color(157,75,35));
			button.setForeground(Color.ORANGE);
			instrumentGroup.add(button);
			instrumentsMenu.add(button);
		}
		
		fc = new JFileChooser();
		fc.setCurrentDirectory(GuiHelper.getSavesDirectory());
		
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
					.build();
		
		content = frame.getContentPane();
		content.setLayout(new BorderLayout());
		
		playButton.setIcon(playIcon);
		playButton.setBorder(null);
		playButton.setToolTipText("Play current song");
		nextPage.setIcon(nextIcon);
		nextPage.setBorder(null);
		nextPage.setToolTipText("Next page");
		previousPage.setIcon(previousIcon);
		previousPage.setBorder(null);
		previousPage.setToolTipText("Previous page");
		clearButton.setIcon(clearIcon);
		clearButton.setBorder(null);
		clearButton.setToolTipText("Clear current song");
		deleteLastButton.setIcon(deleteNoteIcon);
		deleteLastButton.setBorder(null);
		deleteLastButton.setToolTipText("Delete last note");
		setTitleButton.setIcon(titleIcon);
		setTitleButton.setBorder(null);
		setTitleButton.setToolTipText("Set title for current song");
		
		
		menuBar = new MyMenuBar(GuiHelper.getImage(new File("resources/menuback.png")));
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
		
		quitItem.addActionListener(this);
		loadItem.addActionListener(this); 
		saveItem.addActionListener(this); 
		playButton.addActionListener(this);
		clearButton.addActionListener(this);
		nextPage.addActionListener(this);
		previousPage.addActionListener(this);
		deleteLastButton.addActionListener(this);
		setTitleButton.addActionListener(this);
	
		JScrollPane scrollPane = new JScrollPane(text);
		scrollPane.setPreferredSize(new Dimension(210,168));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		mainPanel.add(composerSheet, BorderLayout.CENTER);
		
		panel = new BackgroundPanel(GuiHelper.getImage(GuiHelper.getPanelBackgroundFile()));
		
//		panel.add(scrollPane);
		panel.setLayout(new GridBagLayout());
		gc.anchor = GridBagConstraints.WEST;
		gc.weighty = 0.05;
		gc.weightx = 1;
		gc.gridx = 0;
		gc.gridy = 0;
		panel.add(playButton, gc);
		gc.gridx = 0;
		gc.gridy = 1;
		panel.add(previousPage, gc);
		
		gc.gridx = 1;
		JLabel pageLabel = new JLabel("   PAGE");
		pageLabel.setFont(new Font("Serif", Font.BOLD, 24));
		pageLabel.setForeground(Color.BLACK);
		pageLabel.setMinimumSize(new Dimension(120, 40));
//		pageLabel.setBorder(BorderFactory.createBevelBorder(1));
		gc.weightx = 0.01;
		panel.add(pageLabel, gc);
		gc.weightx =1;
		
		gc.gridx = 2;
		gc.anchor = GridBagConstraints.EAST;
		panel.add(nextPage, gc);
		
		gc.anchor = GridBagConstraints.CENTER;
		gc.weightx = 1;
		gc.gridy = 2;
		gc.gridx = 0;
		panel.add(clearButton, gc);
		
		gc.gridx = 1;
		panel.add(deleteLastButton, gc);
		gc.anchor = GridBagConstraints.WEST;
		gc.weightx = 0;
		gc.gridy = 3;
		gc.gridx = 0;
		gc.gridwidth = 2;
		panel.add(title, gc);
		gc.gridwidth = 1;
		gc.gridx = 2;
		panel.add(setTitleButton, gc);
		
		gc.gridy = 4;
		gc.weighty = 1;
		gc.gridx = 0;
		gc.gridwidth = 3;
		panel.add(titleLabel, gc);
		
		mainPanel.add(panel, BorderLayout.WEST);
//		content.add(panel, BorderLayout.WEST);
		
		mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 5));
		
		content.add(mainPanel);
		frame.setSize(806, 649);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		composerSheet.initialize();
		frame.repaint();
		System.out.println(composerSheet.getSize());

	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		System.out.println(e.getSource()  +"");
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
				Player p = new Player(SongProcessor.getSong(), instrument);
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
					
				text.append("\nCurrent page: " + composerSheet.getPageDisplayed());
				break;
			}
			case PREVIOUS:
			{
				if(PageController.getPages().containsKey(composerSheet.getPageDisplayed() - 1))
				{
					composerSheet.setPageDisplayed(composerSheet.getPageDisplayed() - 1);
				}
				text.append("\nCurrent page: " + composerSheet.getPageDisplayed());
				break;
			}
			case CLEAR:
			{
				PageController.clearNotes();
				composerSheet.setPageDisplayed(1);
				composerSheet.setAllowedX(80);
				SongProcessor.getSong().clear();
				SongProcessor.cleanAllSharps();
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
						state = SaveAndLoad.loadSong(file);
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
						SaveAndLoad.saveSong(state, file);
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
				composerSheet.setAllowedX(composerSheet.getAllowedX() - 25);
				System.out.println("allowed x: " + composerSheet.getAllowedX());
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
		}
	}
	
	public static void main(String[] args) {
		ComposerGui gui = new ComposerGui();
	}

}









