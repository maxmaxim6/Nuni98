//****************************THIS CODE CREATED BY Nuni98***********************************
package myMath;

import java.util.ArrayList;
import java.util.Iterator;

import myMath.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 *
 */
public class Polynom implements Polynom_able
{
	private ArrayList<Monom> Poly = new ArrayList<Monom>();
	/**
	 * Zero (empty polynom)
	 */
	public Polynom() 
	{
	}
	/**
	 * init a Polynom from a String such as:
	 *  {"x", "3+1.4X^3-34x", "(2x^2-4)*(-1.2x-7.1)", "(3-3.4x+1)*((3.1x-1.2)-(3X^2-3.1))"};
	 * @param s: is a string represents a Polynom
	 */
	public Polynom(String s) 
	{
		String t="";
		for(int i=0;i<s.length();i++)
		{
			if(s.charAt(i)!='+')
			{
				if(t.contains("-") && s.charAt(i)=='-')
				{
					Monom m= new Monom(t);
					this.add(m);
					t=""+s.charAt(i);
				}
				else if(s.charAt(i)!='-')
					t+=s.charAt(i);
				else 
				{
					if(t=="")
						t+=s.charAt(i);
					else {
						Monom m= new Monom(t);
						this.add(m);
						t=""+s.charAt(i);
					}
				}
			}
			else
			{
				Monom m= new Monom(t);
				this.add(m);
				t="";
			}
		}
		Monom m= new Monom(t);
		this.add(m);
	}
	@Override
	public Polynom_able copy() 
	{
		Iterator<Monom> i=this.iteretor();
		Polynom_able pp=new Polynom();

		while(i.hasNext())
		{
			Monom m=new Monom(i.next());
			pp.add(m);
		}
		return pp;
	}
	@Override
	public double f(double x)
	{
		Iterator<Monom> i=this.iteretor();
		double ans=0;
		while(i.hasNext())
		{
			ans+=i.next().f(x);
		}
		return ans;
	}
	public void add(Polynom_able p1)
	{
		Iterator<Monom> i=p1.iteretor();
		while(i.hasNext())
			this.add(i.next());
	}
	/** search for m1`s power in our Polynom,
	 * - add the two monoms with the same power.
	 * - if the power dose not match to any monom power, add m1 to the polynom
	 * */
	@Override
	public void add(Monom m1) 
	{
		boolean flag=false;
		Iterator<Monom> i=this.iteretor();
		while(i.hasNext())
		{
			Monom temp=i.next();
			if(temp.get_power()==m1.get_power())
			{
				temp.add(m1);
				flag=true;
			}
		}
		if(!flag)
		{
			Poly.add(m1); // use the Array list add operation
		}
	}
	@Override
	public void substract(Polynom_able p1) 
	{
		Monom m=new Monom("-1");
		p1.multiply(m);
		this.add(p1);
	}
	@Override
	public void multiply(Polynom_able p1)
	{
		Iterator<Monom> i=p1.iteretor();
		Polynom_able temp_poly=this.copy();
		final Polynom_able original_poly = new Polynom(this.toString());
		this.reboot();
		while(i.hasNext())
		{
			temp_poly=new Polynom(original_poly.toString());
			temp_poly.multiply(i.next());
			this.add(temp_poly);
		}
	}
	public void multiply(Monom m1)
	{
		Iterator<Monom> i=this.iteretor();
		while(i.hasNext()) 
		{
			i.next().multiply(m1);
		}
	}
	@Override
	public boolean equals(Polynom_able p1) 
	{

		return this.toString().equals(p1.toString());
	}
	@Override
	public boolean isZero() 
	{
		Iterator<Monom> i=this.iteretor();

		while(i.hasNext())
		{
			if(!i.next().isZero())return false;
		}
		return true;
	}
	@Override
	public double root(double x0, double x1, double eps)
	{
		if(eps<=0)
			throw new RuntimeException("eps should be a positive number!");
		if(this.f(x0)*this.f(x1)> 0)
			throw new RuntimeException("f(x0)*f(x1) should be a negative number!");
		if(Math.abs(f(x0))<eps)return x0;
		if(Math.abs(f(x1))<eps)return x1;
		double m=(x0+x1)/2;
		if(Math.abs(f(m))<eps)return m;
		if(f(m)*f(x0)<0)return root(x0,m,eps);
		if(f(m)*f(x1)<0)return root(m,x1,eps);
		return m;
	}
	@Override
	public Polynom_able derivative()
	{
		Polynom p =new Polynom();
		Iterator<Monom> i=this.iteretor();
		while(i.hasNext())
		{
			p.add(i.next().derivative());
		}
		return p;
	}
	@Override
	public double area(double x0, double x1, double eps) 
	{
		if(x0>=x1) 
		{
			throw new RuntimeException("x0 should be smaller then x1");
		}
		if(eps<=0)
		{
			throw new RuntimeException("eps should be positive");
		}
		double sum=0;
		while (x0<=x1) 
		{
			if(f(x0)<0)
			{
				x0+=eps;
			}
			else
			{
				sum+=f(x0)*eps;
				x0+=eps;
			}
		}
		return sum;
	}
	@Override
	public Iterator<Monom> iteretor()
	{
		Iterator<Monom> itr = Poly.iterator();
		return itr;
	}
	@Override
	public String toString()
	{
		Monom_Comperator comp=new Monom_Comperator();
		Poly.sort(comp);
		Iterator<Monom> i=this.iteretor();
		String ans ="";
		Monom m;
		while(i.hasNext())
		{
			m=i.next();
			if(m.get_coefficient()>0 && ans!="")
				ans+="+";
			ans+= m.toString()!="0" ? m.toString() : "";
		}
		return ans;
	}
	private void reboot() 
	{
		Poly=new ArrayList<Monom>();
	}
}
