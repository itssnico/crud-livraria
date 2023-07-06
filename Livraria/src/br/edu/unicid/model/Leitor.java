package br.edu.unicid.model;

public class Leitor {

	public int codLeitor;
	public String nomeLeitor;
	public String tipoLeitor;

	public Leitor() {
	};

	public Leitor(int codLeitor, String nomeLeitor, String tipoLeitor) {

		this.codLeitor = codLeitor;
		this.nomeLeitor = nomeLeitor;
		this.tipoLeitor = tipoLeitor;
	}

	public int getCodLeitor() {
		return codLeitor;
	}

	public void setCodLeitor(int codLeitor) {
		this.codLeitor = codLeitor;
	}

	public String getNomeLeitor() {
		return nomeLeitor;
	}

	public void setNomeLeitor(String nomeLeitor) {
		this.nomeLeitor = nomeLeitor;
	}

	public String getTipoLeitor() {
		return tipoLeitor;
	}

	public void setTipoLeitor(String tipoLeitor) {
		this.tipoLeitor = tipoLeitor;
	}

}