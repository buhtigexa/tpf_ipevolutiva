package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.text.DefaultCaret;

import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.text.WebTextArea;

public class Console {

	private WebTextArea text;
	private WebScrollPane scroll;
	
	public Console() {
		
		text = new WebTextArea ();
		text.setLineWrap ( false );
		text.setWrapStyleWord ( true );
		text.setMargin ( 5 );
		text.setFont(new Font("monospaced", Font.PLAIN, 16));
		text.setEditable(false);
		text.setDoubleBuffered(true);
		text.setSelectionColor(Color.BLUE);
		scroll = new WebScrollPane ( text, false, true );
		scroll.setPreferredSize ( null );
		
		DefaultCaret caret = (DefaultCaret) text.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
	}

	public void write(String linea){
		text.append(linea);
	}

	public void writeJ(final String linea){
		text.append(linea+"\n");
	}

	public void clear(){
		text.clear();
	}

	public Component getComponent(){
		return scroll;
	}

	public String getText(){
		return text.getText();
	}
	
	
}
