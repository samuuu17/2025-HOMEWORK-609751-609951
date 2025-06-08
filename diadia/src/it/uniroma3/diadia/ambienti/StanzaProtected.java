package it.uniroma3.diadia.ambienti;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.Attrezzo;

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

public class StanzaProtected {

	protected static final  int NUMERO_MASSIMO_DIREZIONI = 4;
	protected static final int NUMERO_MASSIMO_ATTREZZI = 10;

	protected String nome;
	protected Map<String, Attrezzo> nome2attrezzi;
	protected int numeroAttrezzi;

	protected Map<String, StanzaProtected> direzioni2stanze;
	protected int numeroStanzeAdiacenti;
	
	
	public StanzaProtected(String nome) {
		this.nome = nome;
		this.numeroStanzeAdiacenti = 0;
		this.numeroAttrezzi = 0;
		this.direzioni2stanze = new HashMap<>();
		this.nome2attrezzi = new HashMap<>();
	}

	public List<StanzaProtected> getStanzeAdiacenti() {
		return (List<StanzaProtected>) direzioni2stanze.values();
	}

	public void setStanzeAdiacenti(Map<String, StanzaProtected> stanzeAdiacenti) {
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

	
	public void impostaStanzaAdiacente(String direzione, StanzaProtected stanza) {
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

	
	public StanzaProtected getStanzaAdiacente(String direzione) {
		StanzaProtected stanza = null;
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


	public Set<String> getDirezioni() {

		return direzioni2stanze.keySet();
	}

}