package hr.tvz.game.revive;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class KartaSvijeta implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Map<String, Integer> ekspanzijaPoIgracu;

    public KartaSvijeta() {
        this.ekspanzijaPoIgracu = new HashMap<>();
    }

    public void dodajEkspanziju(String imeIgraca){
        int trenutniBrojEkspanzija = ekspanzijaPoIgracu.getOrDefault(imeIgraca, 0);
        ekspanzijaPoIgracu.put(imeIgraca, trenutniBrojEkspanzija + 1);

    }

    public int getBrojEkspanzija(String imeIgraca){
        return ekspanzijaPoIgracu.getOrDefault(imeIgraca, 0);
    }

    public Map<String, Integer> getEkspanzijaPoIgracu() {
        return ekspanzijaPoIgracu;
    }


}
