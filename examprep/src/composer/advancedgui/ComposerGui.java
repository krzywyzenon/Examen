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
		
		Container content = frame.getContentPane();
		content.setLayout(new BorderLayout());
		final JTextArea text = new JTextArea();
		text.setPreferredSize(new Dimension(210,1168));
		
		final JButton playButton = new JButton();
		playButton.setIcon(playIcon);
		JButton clearButton = new JButton("Clear");
		JButton nextPage = new JButton("Next Page");
		JButton previousPage = new JButton("Previous Page");
		playButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				Player p = new Player(SongProcessor.getSong());
				Thread t = new Thread(p);
				t.start();
				
			}
		});
		final ComposerSheet composerSheet = new ComposerSheet(text);
		
		clearButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PageController.clearNotes();
				composerSheet.setPageDisplayed(1);
				composerSheet.setAllowedX(80);
				SongProcessor.getSong().clear();
				SongProcessor.cleanAllSharps();
				composerSheet.initialize();
				
			}
		});
		
		nextPage.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(PageController.getPages().containsKey(composerSheet.getPageDisplayed() + 1))
				{
					composerSheet.setPageDisplayed(composerSheet.getPageDisplayed() + 1);
				}
					
				text.append("\nCurrent page: " + composerSheet.getPageDisplayed());
			}
		});
		
		previousPage.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(PageController.getPages().containsKey(composerSheet.getPageDisplayed() - 1))
				{
					composerSheet.setPageDisplayed(composerSheet.getPageDisplayed() - 1);
				}
				text.append("\nCurrent page: " + composerSheet.getPageDisplayed());
			}
		});
	
		JScrollPane scrollPane = new JScrollPane(text);
		scrollPane.setPreferredSize(new Dimension(210,168));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		content.add(composerSheet, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		
		panel.setPreferredSize(new Dimension(220, 168));
		panel.setMinimumSize(new Dimension(220, 68));
		panel.setMaximumSize(new Dimension(220, 68));
		
		panel.add(scrollPane);
		panel.add(playButton);
		panel.add(clearButton);
		panel.add(previousPage);
		panel.add(nextPage);
		
		content.add(panel, BorderLayout.WEST);
		
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		composerSheet.initialize();

	}

}









