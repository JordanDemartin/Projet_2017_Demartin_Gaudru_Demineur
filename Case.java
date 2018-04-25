public class Case{
	private boolean bombe;
	private boolean ouverte;
	private int flag;

	public Case(){
		this.bombe=false;
		this.ouverte=false;
		this.flag=0;
	}

	public void setBombe(){
		this.bombe=true;
	}

	public boolean getBombe(){
		return this.bombe;
	}

	public boolean tryDecouvre(){
		if(this.ouverte==false){
			if(this.bombe && this.flag==0)
				return true;
			if(this.flag==0)
				this.ouverte=true;
		}
		return false;
	}

	public void nextFlag(){
		this.flag++;
		if(this.flag==3)
			this.flag=0;
	}

	public int getFlag(){
		return this.flag;
	}
}