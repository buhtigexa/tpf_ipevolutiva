package ui;

import com.alee.extended.statusbar.WebStatusLabel;

public class ITimer implements Runnable{

	public boolean cronActive = false;
	private WebStatusLabel time;
	private Long ms;
	
	public ITimer (WebStatusLabel label){
		this.time = label;
		ms = new Long(0);
	}
	
	@Override
	public void run() {
		Integer hours = 0,minutes = 0 , seconds = 0, mils = 0;
		String h="",min="", seg="", mil="";
		time.setText( "00:00:00:000" );
		try
		{
			while( cronActive )
			{
				Thread.sleep( 4 );
				mils += 4;
				ms += 4;
				if( mils == 1000 )
				{
					mils = 0;
					seconds += 1;
					if( seconds == 60 )
					{
						seconds = 0;
						minutes++;
						
						if (minutes == 60){
							hours++;
							minutes = 0;
						}
					}
				}
				h = hours.toString();
				if( minutes < 10 ) min = "0" + minutes;
				else min = minutes.toString();
				if( seconds < 10 ) seg = "0" + seconds;
				else seg = seconds.toString();
				if( mils < 10 ) mil = "00" + mils;
				else if( mils < 100 ) mil = "0" + mils;
				else mil = mils.toString();
				time.setText(h + ":" + min + ":" + seg + ":" + mil ); 
			}
		}catch(Exception e){}
	}

	public void start(){
		cronActive = true;
		Thread t = new Thread(this);
		ms = new Long(0);
		t.start();
	}
	
	public void stop(){
		cronActive = false;
	}

	public void reset() {
		time.setText("00:00:00:000");
		ms = new Long(0);
	}
	
	public String toString(){
		return time.getText();
	}

	public Long getMs(){
		return this.ms;
	}

}
