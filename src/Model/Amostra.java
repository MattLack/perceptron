package Model;

import java.util.List;

public class Amostra {

	private List<Double> caracteristicas;
	private String classeReal;
	private String classeAtribuida;

	public Amostra() {

	}

	public List<Double> getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(List<Double> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public String getClasseReal() {
		return classeReal;
	}

	public void setClasseReal(String classeReal) {
		this.classeReal = classeReal;
	}

	public String getClasseAtribuida() {
		return classeAtribuida;
	}

	public void setClasseAtribuida(String classeAtribuida) {
		this.classeAtribuida = classeAtribuida;
	}

}