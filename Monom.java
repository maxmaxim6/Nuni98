
package myMath;

import java.util.Comparator;


/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 *
 */
public class Monom implements function
{
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	public static Comparator<Monom> getComp()
	{
		return _Comp;
	}
	public Monom(double a, int b)
	{
		this.set_coefficient(a);
		this.set_power(b);
	}
	public Monom(Monom ot) 
	{
		this(ot.get_coefficient(), ot.get_power());
	}

	public double get_coefficient()
	{
		return this._coefficient;
	}
	public int get_power()
	{
		return this._power;
	}
	/** 
	 * this method returns the derivative monom of this.
	 * @return
	 */
	public Monom derivative()
	{
		if(this.get_power()==0)
		{
			return getNewZeroMonom();
		}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}
	public double f(double x) 
	{
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	} 
	public boolean isZero() 
	{
		return this.get_coefficient() == 0;
	}
	// *********************** add your code below **********************
	public Monom(String s) 
	{
		if(s==null)throw new RuntimeException("string is null!");
		if(s=="")throw new RuntimeException("string is empty!");
		try {
			boolean x=false;
			boolean flag = isPositive(s);
			int i= flag ? 0 : 1;
			int pow=0;
			double coef=0;
			String t="";
			if(s.charAt(i)=='0')
			{
				getNewZeroMonom();
				return;
			}
			while(i<s.length()&& s.charAt(i)< 58 && s.charAt(i)> 47 ||i<s.length()&& s.charAt(i)=='.')
			{
				t+=s.charAt(i++);
			}
			if(t!="")
			{
				Double d = Double.parseDouble(t);
				coef=d;
			}
			if(s.contains("x"))
			{
				coef = coef==0 ? 1 : coef;
				i++;
				x=true;
			}
			if(s.contains("^"))
			{
				i++;
				t="";
				while(i<s.length())
				{
					t+=s.charAt(i++);
				}
				Integer d = Integer.parseInt(t);
				pow=d;
			}
			else 
				pow = x? 1:0;
			if (!flag) coef*= -1;
			this.set_coefficient(coef);
			this.set_power(pow);
		}
		catch (NumberFormatException e)
		{
			throw new NumberFormatException("Power should be positive");
		}
	}
	public boolean equals(Monom m)
	{
		return this.toString().equals(m.toString());
	}
	public void add(Monom m) 
	{
		if(m.get_power()!= this.get_power()) 
			throw new RuntimeException("Error! - powers don`t match");
		this._coefficient+=m.get_coefficient();
		if(this._coefficient==0)
			_power=0;
	}

	public void multiply(Monom d) 
	{
		this._coefficient*=d._coefficient;
		this._power+=d._power;
	}

	public String toString() 
	{
		if(isZero())return "0";
		if(this._power == 0) return ""+_coefficient;
		else if(this._power== 1)
		{
			if(Math.abs(_coefficient)==1)
				return _coefficient==1? "x":"-x";
			else
				return _coefficient+"x";
		}
		else   // if power > 1
		{
			if(Math.abs(_coefficient)==1)
				return _coefficient==1? "x^"+this._power:"-x^"+this._power;
			else
				return _coefficient+"x^"+this._power;
		}
	}
	//****************** Private Methods and Data *****************

	private boolean isPositive(String s)
	{
		return s.charAt(0)!= '-';
	}
	private void set_coefficient(double a)
	{
		this._coefficient = a;
	}
	private void set_power(int p)
	{
		if(p<0) 
			throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);
		this._power = p;
	}
	private static Monom getNewZeroMonom()
	{
		return new Monom(ZERO);
	}
	private double _coefficient; 
	private int _power;
}
