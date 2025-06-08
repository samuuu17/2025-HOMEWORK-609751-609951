package it.uniroma3.diadia.ambienti;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
 */

public class Stanza {

	static final private int NUMERO_MASSIMO_DIREZIONI = 4;
	private static final int NUMERO_MASSIMO_ATTREZZI = 10;

	private String nome;
	private Map<String, Attrezzo> nome2attrezzi;
	private int numeroAttrezzi;

	private Map<Direzione, Stanza> direzioni2stanze;
	private int numeroStanzeAdiacenti;
	private AbstractPersonaggio personaggio;
	
	
	public Stanza(String nome) {
		this.nome = nome;
		this.numeroStanzeAdiacenti = 0;
		this.numeroAttrezzi = 0;
		this.direzioni2stanze = new HashMap<>();
		this.nome2attrezzi = new HashMap<>();
	}

	public List<Stanza> getStanzeAdiacenti() {
		List<Stanza> listaStanzeAdiacenti = new ArrayList<>();
		for (Stanza s : direzioni2stanze.values()) {
			listaStanzeAdiacenti.add(s);
		}
		return listaStanzeAdiacenti;
	}

	public void setStanzeAdiacenti(Map<Direzione, Stanza> stanzeAdiacenti) {
		this.direzioni2stanze = stanzeAdiacenti;
	}

	public int getNumeroStanzeAdiacenti() {
		return numeroStanzeAdiacenti;
	}

	public void setNumeroStanzeAdiacenti(int numeroStanzeAdiacenti) {
		this.numeroStanzeAdiacenti = numeroStanzeAdiacenti;
	}

	public int getNumeroAttrezziPossibili() {
		return NUMERO_MASSIMO_ATTREZZI-this.numeroAttrezzi;
	}

	
	public void impostaStanzaAdiacente(Direzione direzione, Stanza stanza) {
		boolean aggiornato = false;
				if (direzioni2stanze.containsKey(direzione)) {
			this.direzioni2stanze.put(direzione,stanza);
			aggiornato = true;
		}
		if (!aggiornato)
			if (this.numeroStanzeAdiacenti < NUMERO_MASSIMO_DIREZIONI) {
				this.direzioni2stanze.put(direzione,stanza);
				this.numeroStanzeAdiacenti++;
			}
	}

	
	public Stanza getStanzaAdiacente(Direzione direzione) {
		Stanza stanza = null;
		if (this.direzioni2stanze.containsKey(direzione))
			stanza = this.direzioni2stanze.get(direzione);
		return stanza;
	}

	
	public String getNome() {
		return this.nome;
	}

	
	public String getDescrizione() {
		return this.toString();
	}

	public Collection<Attrezzo> getAttrezzi() {
		return this.nome2attrezzi.values();
	}

	
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (attrezzo != null && this.numeroAttrezzi < NUMERO_MASSIMO_ATTREZZI) {
			this.nome2attrezzi.put(attrezzo.getNome(), attrezzo);
			this.numeroAttrezzi++;
			return true;
		}
		else {
			return false;
		}
	}

	
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: ");
		risultato.append(this.getDirezioni().toString());
		risultato.append("\nAttrezzi nella stanza: ");
		risultato.append(this.getAttrezzi().toString());
		return risultato.toString();
	}

	
	public boolean hasAttrezzo(String nomeAttrezzo) {	
		return this.nome2attrezzi.containsKey(nomeAttrezzo);
	}

	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo attrezzoCercato;
		attrezzoCercato = null;
		if (this.nome2attrezzi.containsKey(nomeAttrezzo))
			attrezzoCercato = this.nome2attrezzi.get(nomeAttrezzo);
		return attrezzoCercato;	
	}

	
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		if(attrezzo!=null){
			this.nome2attrezzi.remove(attrezzo.getNome(), attrezzo);

			return true;
		}
		else
			return false;
	}


	public Set<Direzione> getDirezioni() {

		return direzioni2stanze.keySet();
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stanza that = (Stanza) obj;
		return this.getNome().equals(that.getNome());
	}

	public void setPersonaggio(AbstractPersonaggio ap) {
		this.personaggio = ap;
	}
	
	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}
	

	public int getNumeroAttrezzi() {
		return numeroAttrezzi;
	}

	public void setNumeroAttrezzi(int numeroAttrezzi) {
		this.numeroAttrezzi = numeroAttrezzi;
	}


}