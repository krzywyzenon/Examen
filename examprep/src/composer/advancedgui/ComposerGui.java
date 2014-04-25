package composer.advancedgui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import composer.controller.Player;




public class ComposerGui {

	
	public static void main(String[] args) {
		ImageIcon playIcon = new ImageIcon(GuiHelper.getImage(GuiHelper.getPlayKeyFile()));
		JFrame frame = new JFrame("Composer");
		//Creates a frame with a title of "Paint it"
		
		Container content = frame.getContentPane();
		//Creates a new container
		content.setLayout(new BorderLayout());
		//sets the layout
		JTextArea text = new JTextArea();
		text.setPreferredSize(new Dimension(210,1168));
		
		final JButton playButton = new JButton();
		playButton.setIcon(playIcon);
		JButton clearButton = new JButton("Clear");
		playButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				Player p = new Player(SongProcessor.getSong());
				Thread t = new Thread(p);
				t.start();
				
			}
		});
		final PadDrawMine drawPad = new PadDrawMine(text);
		
		clearButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PadDrawMine.getDrawnNotes().clear();
				drawPad.setAllowedX(80);
				SongProcessor.getSong().clear();
				SongProcessor.cleanAllSharps();
				drawPad.initialize();
				
			}
		});
	
		JScrollPane scrollPane = new JScrollPane(text);
		scrollPane.setPreferredSize(new Dimension(210,168));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		content.add(drawPad, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		
		panel.setPreferredSize(new Dimension(220, 168));
		panel.setMinimumSize(new Dimension(220, 68));
		panel.setMaximumSize(new Dimension(220, 68));
		
		panel.add(scrollPane);
		panel.add(playButton);
		panel.add(clearButton);
		
		content.add(panel, BorderLayout.WEST);
		//sets the panel to the left
		
		frame.setSize(800, 600);
		//sets the size of the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//makes it so you can close
		frame.setVisible(true);
		//makes it so you can see it
		drawPad.initialize();

	}

}









