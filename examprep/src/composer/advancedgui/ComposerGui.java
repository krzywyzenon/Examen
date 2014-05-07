package composer.advancedgui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import com.google.common.collect.ImmutableMap;
import composer.controller.Player;
import composer.controller.SaveAndLoad;
import composer.controller.State;
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
	
	private ComposerSheet composerSheet;
	
	private ImageIcon playIcon;
	private ImageIcon nextIcon;
	private ImageIcon previousIcon;
	private ImageIcon clearIcon;
	private JTextArea text;
	private JFrame frame;
	
	private BackgroundPanel mainPanel;
	
	private Container content;
	
	private JButton clearButton;
	private JButton nextPage;
	private JButton previousPage;
	private JButton playButton;
	private JMenuItem loadItem;
	private JMenuItem saveItem;
	private JMenuItem quitItem;
	
	private MyMenuBar menuBar;
	private JMenu fileMenu;
	
	private BackgroundPanel panel;
	
	private JFileChooser fc;
	
	public ComposerGui() {
		playIcon = new ImageIcon(GuiHelper.getImage(GuiHelper.getPlayKeyFile()));
		nextIcon = new ImageIcon(GuiHelper.getImage(GuiHelper.getNextPageKeyFile()));
		previousIcon = new ImageIcon(GuiHelper.getImage(GuiHelper.getPreviousPageKeyFile()));
		clearIcon = new ImageIcon(GuiHelper.getImage(GuiHelper.getClearKeyFile()));
		Image background = GuiHelper.getImage(GuiHelper.getMainBackgroundFile());
		frame = new JFrame("Composer");
		text = new JTextArea();
		text.setPreferredSize(new Dimension(210,1168));
		mainPanel = new BackgroundPanel(background);
		mainPanel.setLayout(new BorderLayout());
		
		composerSheet  = new ComposerSheet(text);
		clearButton = new JButton();
		nextPage = new JButton();
		previousPage = new JButton();
		playButton = new JButton();

		loadItem = new JMenuItem("Load song");
		loadItem.setBackground(new Color(157,75,35));
		loadItem.setForeground(Color.ORANGE);
		saveItem = new JMenuItem("Save song");
		saveItem.setBackground(new Color(157,75,35));
		saveItem.setForeground(Color.ORANGE);
		quitItem = new JMenuItem("Quit");
		quitItem.setBackground(new Color(157,75,35));
		quitItem.setForeground(Color.ORANGE);
		
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
					.build();
		
		content = frame.getContentPane();
		content.setLayout(new BorderLayout());
		
		playButton.setIcon(playIcon);
		playButton.setBorder(null);
		nextPage.setIcon(nextIcon);
		nextPage.setBorder(null);
		previousPage.setIcon(previousIcon);
		previousPage.setBorder(null);
		clearButton.setIcon(clearIcon);
		clearButton.setBorder(null);
		
		
		menuBar = new MyMenuBar(GuiHelper.getImage(new File("resources/menuback.png")));
		//menuBar.setBackground(new Color(157,75,35));
		menuBar.setBorder(null);
		fileMenu = new JMenu("File");
		fileMenu.setForeground(Color.ORANGE);
		fileMenu.add(saveItem);
		fileMenu.add(loadItem);
		fileMenu.add(new JSeparator());
		fileMenu.add(quitItem);
		menuBar.add(fileMenu);
		frame.setJMenuBar(menuBar);
		
		quitItem.addActionListener(this);
		loadItem.addActionListener(this); 
		saveItem.addActionListener(this); 
		playButton.addActionListener(this);
		clearButton.addActionListener(this);
		nextPage.addActionListener(this);
		previousPage.addActionListener(this);
	
		JScrollPane scrollPane = new JScrollPane(text);
		scrollPane.setPreferredSize(new Dimension(210,168));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		
//		content.add(composerSheet, BorderLayout.CENTER);
		mainPanel.add(composerSheet, BorderLayout.CENTER);
		
		panel = new BackgroundPanel(GuiHelper.getImage(GuiHelper.getPanelBackgroundFile()));
//		panel = new BackgroundPanel();
		
//		panel.setPreferredSize(new Dimension(220, 168));
//		panel.setMinimumSize(new Dimension(220, 68));
//		panel.setMaximumSize(new Dimension(220, 68));
		
//		panel.add(scrollPane);
		panel.add(playButton);
		panel.add(clearButton);
		panel.add(previousPage);
		panel.add(nextPage);
		
		mainPanel.add(panel, BorderLayout.WEST);
//		content.add(panel, BorderLayout.WEST);
		
		mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 5));
		
		content.add(mainPanel);
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		composerSheet.initialize();
		frame.repaint();
		System.out.println(menuBar.getSize());

	}

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
				Player p = new Player(SongProcessor.getSong());
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
					State state = new State(SongProcessor.getSong(), PageController.getPages(), SoundDrawRelations.getDrawingsAndSoundsRelations());
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
		}
	}
	
	public static void main(String[] args) {
		ComposerGui gui = new ComposerGui();
	}

}









