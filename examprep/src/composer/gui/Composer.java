package composer.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import composer.controller.Player;
import composer.controller.VolumeController;
import composer.data.Lengths;
import composer.data.Tones;
import composer.sound.Note;
import composer.sound.Song;

public class Composer extends JFrame {

	private static final long serialVersionUID = -6911938639603955230L;
	
	Composer composer;
	
	Song song = new Song();
	
	Lengths length = Lengths.QUARTER;
	
	Integer volume = VolumeController.getVolume();
	String lengthValue = "QUARTER";
	
	//Panels
	JPanel controllPanel = new JPanel();
	JPanel songControllPanel = new JPanel();
	JPanel songControllVolumePanel = new JPanel();
	JPanel songControllVolumeButtonsPanel = new JPanel();
	JPanel songConrollPlayerPanel = new JPanel();
	JPanel composerControllPanel = new JPanel();
	JPanel composerControllLengthPanel = new JPanel();
	JPanel composerControllNotePanel = new JPanel();
	JPanel dataPanel = new JPanel();
	JPanel songInfoPanel = new JPanel();
	
	JTextArea sheet = new JTextArea();
	
	//Labels
	JLabel volumeLabel = new JLabel("Volume:");
	JLabel volumeValue = new JLabel(VolumeController.getVolume().toString());
	JLabel composerLabel = new JLabel("Compose");
	JLabel noteLengthLabel = new JLabel("Note length");
	JLabel noteLabel = new JLabel("Note");
	
	//Buttons
	JButton volumeUp = new JButton("volUp");
	JButton volumeDown = new JButton("volDown");
	JButton playButton = new JButton("Play");
	JButton eightsButton = new JButton("Eight");
	JButton quartersButton = new JButton("Quarter");
	JButton halvesButton = new JButton("Half");
	JButton wholesButton = new JButton("Whole");
	JButton cNoteButton = new JButton("C");
	JButton cisNoteButton = new JButton("CIS");
	JButton dNoteButton = new JButton("D");
	JButton disNoteButton = new JButton("DIS");
	JButton eNoteButton = new JButton("E");
	JButton fNoteButton = new JButton("F");
	JButton fisNoteButton = new JButton("FIS");
	JButton gNoteButton = new JButton("G");
	JButton gisNoteButton = new JButton("GIS");
	JButton aNoteButton = new JButton("A");
	JButton aisNoteButton = new JButton("AIS");
	JButton bNoteButton = new JButton("B");
	JButton c2NoteButton = new JButton("C2");
	
	public Composer(String name)
	{
		super(name);

		dataPanel.setLayout(new GridLayout(2,1));
		dataPanel.add(sheet);
		dataPanel.add(songInfoPanel);
		
		songControllPanel.setLayout(new GridLayout(1,2));
		songControllVolumePanel.setLayout(new GridLayout(2,1));
		songControllVolumeButtonsPanel.setLayout(new GridLayout(1,3));
		controllPanel.setLayout(new GridLayout(2,1));
		composerControllPanel.setLayout(new GridLayout(3,1));
		composerControllLengthPanel.setLayout(new GridLayout(1,5));
		composerControllNotePanel.setLayout(new GridLayout(1,14));
		
		songControllVolumeButtonsPanel.add(volumeDown);
		songControllVolumeButtonsPanel.add(volumeValue);
		songControllVolumeButtonsPanel.add(volumeUp);
		
		songControllVolumePanel.add(volumeLabel);
		songControllVolumePanel.add(songControllVolumeButtonsPanel);
		
		songConrollPlayerPanel.add(playButton);

		songControllPanel.add(songControllVolumePanel);
		songControllPanel.add(songConrollPlayerPanel);
				
		composerControllLengthPanel.add(noteLengthLabel);
		composerControllLengthPanel.add(eightsButton);
		composerControllLengthPanel.add(quartersButton);
		composerControllLengthPanel.add(halvesButton);
		composerControllLengthPanel.add(wholesButton);
		
		composerControllNotePanel.add(noteLabel);
		composerControllNotePanel.add(cNoteButton);
		composerControllNotePanel.add(cisNoteButton);
		composerControllNotePanel.add(dNoteButton);
		composerControllNotePanel.add(disNoteButton);
		composerControllNotePanel.add(eNoteButton);
		composerControllNotePanel.add(fNoteButton);
		composerControllNotePanel.add(fisNoteButton);
		composerControllNotePanel.add(gNoteButton);
		composerControllNotePanel.add(gisNoteButton);
		composerControllNotePanel.add(aNoteButton);
		composerControllNotePanel.add(aisNoteButton);
		composerControllNotePanel.add(bNoteButton);
		composerControllNotePanel.add(c2NoteButton);
		
		composerControllPanel.add(composerLabel);
		composerControllPanel.add(composerControllLengthPanel);
		composerControllPanel.add(composerControllNotePanel);
		
		controllPanel.add(songControllPanel);
		controllPanel.add(composerControllPanel);
			

		this.setSize(800, 600);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(1,2));
		
		
		this.add(controllPanel);
		this.add(dataPanel);
		
		composer = this;
		
		playButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Player p = new Player(song);
				Thread t = new Thread(p);
				t.start();
			}
		});
		
		eightsButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				length = Lengths.EIGHT;
				lengthValue = "EIGHT";
			}
		});
		
		quartersButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				length = Lengths.QUARTER;
				lengthValue = "QUARTER";
			}
		});
		
		halvesButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				length = Lengths.HALF;
				lengthValue = "HALF";
			}
		});
		
		wholesButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				length = Lengths.WHOLE;
				lengthValue = "WHOLE";
			}
		});
		
		cNoteButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				song.add(new Note(Tones.C, length));
				sheet.append(lengthValue + " C, ");
			}
		});
		
		cisNoteButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				song.add(new Note(Tones.CIS, length));
				sheet.append(lengthValue + " CIS, ");
			}
		});
		
		dNoteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				song.add(new Note(Tones.D, length));
				sheet.append(lengthValue + " D, ");
				
			}
		});
		
		disNoteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				song.add(new Note(Tones.DIS, length));
				sheet.append(lengthValue + " DIS, ");
				
			}
		});
		
		eNoteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				song.add(new Note(Tones.E, length));
				sheet.append(lengthValue + " E, ");
				
			}
		});
		
		fNoteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				song.add(new Note(Tones.F, length));
				sheet.append(lengthValue + " F, ");
				
			}
		});
		
		fisNoteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				song.add(new Note(Tones.FIS, length));
				sheet.append(lengthValue + " FIS, ");
				
			}
		});
		
		gNoteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				song.add(new Note(Tones.G, length));
				sheet.append(lengthValue + " G, ");
				
			}
		});
		
		gisNoteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				song.add(new Note(Tones.GIS, length));
				sheet.append(lengthValue + " GIS, ");
				
			}
		});
		
		aNoteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				song.add(new Note(Tones.A, length));
				sheet.append(lengthValue + " A, ");
				
			}
		});
		
		aisNoteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				song.add(new Note(Tones.AIS, length));
				sheet.append(lengthValue + " AIS, ");
				
			}
		});
		
		bNoteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				song.add(new Note(Tones.B, length));
				sheet.append(lengthValue + " B, ");
				
			}
		});
		
		c2NoteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				song.add(new Note(Tones.C2, length));
				sheet.append(lengthValue + " CIS, ");
				
			}
		});
		
		volumeDown.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VolumeController.setVolumeDown(100);
				composer.repaint();
				volumeValue.setText(VolumeController.getVolume().toString());
			}
		});
		
		volumeUp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VolumeController.setVolumeUp(100);
				volumeValue.setText(VolumeController.getVolume().toString());
				
			}
		});
	}
	

}
