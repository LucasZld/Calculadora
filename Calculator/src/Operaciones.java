import java.util.ArrayList;
import java.util.Arrays;

public class Operaciones {
	
	private int indexO = 0;
	private int indexN = 0;
	private float resultado;

	public float operar(ArrayList<Float> nums, ArrayList<String> ops) {
		ops.add(")");
		int i = indexO, j = indexN;
		parentesis(nums, ops, i, j);
		primerOperador(nums, ops, i, j);
		segundoOperador(nums, ops, i, j);
		tercerOperador(nums, ops, i, j);
		resultado = nums.get(0);
		return resultado;
	}

	public void parentesis (ArrayList<Float> nums, ArrayList<String> ops, int i, int j) {
		while (ops.get(i)!=")") {
			switch (ops.get(i)) {
			case "(":
					ops.remove(i);
					indexO = i;
					indexN = j;
					operar(nums, ops);
				break;
			case "V", "!", "~":
					i++;
				break;
			default:
					i++;
					j++;
				break;
			}
		}
	}
	
	public void primerOperador(ArrayList<Float> nums, ArrayList<String> ops, int i, int j) {
		while (ops.get(i)!=")") {
			switch (ops.get(i)) {
			case "V":
				nums.set(i, raizCuadrada(nums.get(i)));
				ops.remove(i);
				break;
			case "!":
				nums.set(i, factorial(nums.get(i)));
				ops.remove(i);
				break;
			case "~":
				nums.set(i, negar(nums.get(i)));
				ops.remove(i);
				break;
			default:
					i++;
				break;
			}
			j++;
		}
	}
	
	public void segundoOperador(ArrayList<Float> nums, ArrayList<String> ops, int i, int j) {
		while (ops.get(i)!=")") {
			switch (ops.get(i)) {
			case "x":
				nums.set(i, mult(nums.get(i),nums.get(i+1)));
				nums.remove(i+1);
				ops.remove(i);
				break;
			case "/":
				nums.set(i, div(nums.get(i),nums.get(i+1)));
				nums.remove(i+1);
				ops.remove(i);
				break;
			case "%":
				nums.set(i, mod(nums.get(i),nums.get(i+1)));
				nums.remove(i+1);
				ops.remove(i);
				break;
			case "^":
				nums.set(i, elevar(nums.get(i),nums.get(i+1)));
				nums.remove(i+1);
				ops.remove(i);
				break;
			default:
					i++;
					j++;
				break;
			}
		}
	}
	
	public void tercerOperador(ArrayList<Float> nums, ArrayList<String> ops, int i, int j) {
		while (ops.get(i)!=")") {
			switch (ops.get(i)) {
			case "+":
				nums.set(i, suma(nums.get(i),nums.get(i+1)));
				nums.remove(i+1);
				ops.remove(i);
				break;
			case "-":
				nums.set(i, resta(nums.get(i),nums.get(i+1)));
				nums.remove(i+1);
				ops.remove(i);
				break;
			default:
				System.err.println("Operador erróneo");
				return;
			}
		}
	}
	
	public float getResultado() {
		return resultado;
	}
	
	public float suma(float num1, float num2) {
		return (num1+num2);
	}
	
	public float resta(float num1, float num2) {
		return (num1-num2);
	}
	
	public float mult(float num1, float num2) {
		return (num1*num2);
	}
	
	public float div(float num1, float num2) {
		return (num1/num2);
	}
	
	public float mod(float num1, float num2) {
		return (num1%num2);
		
	}
	
	public float raizCuadrada(float num) {
		float raiz = (float) Math.sqrt(num);
		return raiz;
	}
	
	public float elevar(float num1, float num2) {
		float elevado = (float) Math.pow(num1,num2);
		return elevado;
	}
	
	public float factorial(float num) {
		if (num==1) {return 1;}
		else {return num * factorial(num-1);}
	}
	
	public float negar (float num) {
		return -num;
	}
	
	


	//***********For Testing*************/
	
	public ArrayList<Float> nums() {
		ArrayList<Float> n = new ArrayList<Float>(Arrays.asList(4f, 4f, 2f));
		return n;
	}
	
	public ArrayList<String> sim() {
		ArrayList<String> n = new ArrayList<String>(Arrays.asList("(","+","~",")"));
		return n;
	}

	public static void main(String[] args) {
		Operaciones t = new Operaciones();
		System.out.println(t.operar(t.nums(), t.sim()));
	}
}

