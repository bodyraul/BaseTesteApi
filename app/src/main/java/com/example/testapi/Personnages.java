package com.example.testapi;

import java.util.ArrayList;
import java.util.List;

public class Personnages {
    private List<String> JediBleu=new ArrayList<>();
    private List<String> JediVert=new ArrayList<>();
    private List<String> JediVilolet=new ArrayList<>();
    private List<String> Sith=new ArrayList<>();
    private List<String> Humain=new ArrayList<>();
    private List<String> droid=new ArrayList<>();

   public void creationList(){
       JediBleu.add("Luke Skywalker");JediBleu.add("Yoda");JediBleu.add("Qui-Gon Jinn");
       JediVert.add("Obi-Wan Kenobi");
       JediVilolet.add("Mace Windu");
       Sith.add("Darth Vader");Sith.add("Anakin Skywalker");Sith.add("Palpatine");Sith.add("Darth Maul");Sith.add("Dooku");
       droid.add("C-3PO"); droid.add("R4-P17"); droid.add("R2-D2"); droid.add("R5-D4"); droid.add("IG-88");
       Humain.add("Leia Organa");Humain.add("Owen Lars");Humain.add("Beru Whitesun lars");Humain.add("Biggs Darklighter");Humain.add("Wilhuff Tarkin");Humain.add("Han Solo");Humain.add("Wedge Antilles");Humain.add("Boba Fett");Humain.add("Lando Calrissian");Humain.add("Mon Mothma");Humain.add("Padmé Amidala");Humain.add("Ric Olié");Humain.add("Quarsh Panaka");Humain.add("Shmi Skywalker");Humain.add("Cordé,Dormé");Humain.add("Bail Prestor Organa");Humain.add("Jango Fett,Jocasta Nu");Humain.add("Raymus Antilles");

   }

    public List<String> getJediBleu() {
        return JediBleu;
    }

    public List<String> getJediVert() {
        return JediVert;
    }

    public List<String> getSith() {
        return Sith;
    }

    public List<String> getHumain() {
        return Humain;
    }

    public List<String> getDroid() {
        return droid;
    }

    public List<String> getJediVilolet() {
        return JediVilolet;
    }


}
