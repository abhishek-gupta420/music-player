package musicplayer;

import java.io.File; 
import java.io.IOException;  
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.UnsupportedAudioFileException; 


public class MusicPlayer {
 
  
    // to store current position 
	Long currentFrame; 
	Clip clip; 
	
	// current status of clip 
	String status; 
	
	AudioInputStream audioInputStream; 
	static String filePath; 
                
    
        public MusicPlayer()throws UnsupportedAudioFileException, 
		IOException, LineUnavailableException {
    
            
            	// create AudioInputStream object 
		audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile()); 
		
		// create clip reference 
		clip = AudioSystem.getClip(); 
		
		// open audioInputStream to the clip 
		//clip.open(audioInputStream); 
		
		clip.loop(Clip.LOOP_CONTINUOUSLY);
      
        }
  
 
    
    // Method to play the audio 
	public void play() 
	{ 
		//start the clip 
		clip.start(); 
		
		status = "play"; 
	} 
	
	// Method to pause the audio 
	public void pause() 
	{ 
		if (status.equals("paused")) 
		{ 
			System.out.println("audio is already paused"); 
			return; 
		} 
		this.currentFrame = this.clip.getMicrosecondPosition(); 
		clip.stop(); 
		status = "paused"; 
	} 
	
	// Method to resume the audio 
	public void resumeAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException 
	{ 
		if (status.equals("play")) 
		{ 
			System.out.println("Audio is already "+ "being played"); 
			return; 
		} 
              /*  else if (status==null)
                {
                    resetAudioStream(); 
		currentFrame = 0L; 
		clip.setMicrosecondPosition(0); 
                    this.play();
                }*/
                
		clip.close(); 
		resetAudioStream(); 
		clip.setMicrosecondPosition(currentFrame); 
		this.play(); 
                
	} 
	
	// Method to restart the audio 
	public void restart() throws IOException, LineUnavailableException,UnsupportedAudioFileException 
	{ 
		clip.stop(); 
		clip.close(); 
		resetAudioStream(); 
		currentFrame = 0L; 
		clip.setMicrosecondPosition(0); 
		this.play(); 
	} 
	
	// Method to stop the audio 
	public void stop() throws UnsupportedAudioFileException, 
	IOException, LineUnavailableException 
	{ 
		currentFrame = 0L; 
               clip.stop();
		clip.close(); 
	} 
	
	
	
	// Method to reset audio stream 
	public void resetAudioStream() throws UnsupportedAudioFileException, IOException, 
											LineUnavailableException 
	{ 
		audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile()); 
		clip.open(audioInputStream); 
		clip.loop(Clip.LOOP_CONTINUOUSLY); 
	} 
        
   
        
        // Main Method of the whole program
        
        
           public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

          filePath = "H:\\java\\mainduniya.wav"; 
               JFrame f=new JFrame("Music Player : ");
               JButton b1=new JButton("Resume");
                JButton b2=new JButton("Pause");
                 JButton b3=new JButton("Stop");
                 JButton b4=new JButton("Start");
                 
                 // setting colors of buttons
                 b1.setBackground(Color.yellow);
                 b2.setBackground(Color.GREEN);
                 b3.setBackground(Color.CYAN);
                 b4.setBackground(Color.PINK);
                 
                 // adding action listeners to the buttons
                 MusicPlayer mp=new MusicPlayer();
             b1.addActionListener(new ActionListener(){
                   @Override
                   public void actionPerformed(ActionEvent ae) {
                       try {
                         
                             
                              
                              
                               mp.resumeAudio();
                              
                           
                       } catch (UnsupportedAudioFileException ex) {
                           Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
                       } catch (IOException ex) {
                           Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
                       } catch (LineUnavailableException ex) {
                           Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
                       }
                   }
                 
             });
                  b2.addActionListener(new ActionListener(){
                   @Override
                   public void actionPerformed(ActionEvent ae) {
                     mp.pause();
                   }
                 
             });
                       b3.addActionListener(new ActionListener(){
                   @Override
                   public void actionPerformed(ActionEvent ae) {
                       try {
                           
                           mp.stop();
                       } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                           Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
                       }
                   }
                 
             });
                            b4.addActionListener(new ActionListener(){
                   @Override
                   public void actionPerformed(ActionEvent ae) {
                       try {
                           mp.restart();
                       } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
                           Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
                       }
                   }
                 
             });
                 
                 JPanel p=new JPanel();
                 JPanel p1=new JPanel();
                // p1.setBounds(0,0,200,50);
                 p.setBounds(0,51,200,149);
                 p.setBackground(Color.yellow);
                 p1.setBackground(Color.GREEN);
              p.setLayout(new GridLayout(2,2));
                 p.add(b1);
                 p.add(b2);
                 p.add(b3);
                 p.add(b4);
                f.add(p);
              //  f.add(p1);
               f.setSize(200,250);
             
       f.setVisible(true);
       f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
               
    
    }

}
