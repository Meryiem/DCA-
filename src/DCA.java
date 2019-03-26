
import java.util.*;
import java.io.*;

class DCA {

    float maxMCAVPositife=-1,maxMCAVNull=-5;
    static float maxTP=-1;
    float TP = -5;
    float FN = -5;
    float FP = -5;
    float TN = -5;
    float DR=0,FAR=0;
    int Continu = 1;
    int aleatoire = 2;
    int Ordre = aleatoire;//////////////////////////////////////////
    int nbEx = 0;
    int normal = 0, anormal = 0;

    int MCAVPositife = 0, MCAVNull = 0;
    String fichier = "kddcup2.txt";

    
    Vector<DCs> dcs100 ;
    DCs dcs10[] = new DCs[10];
    Entrer entrer[];
    Vector<Entrer> entrerDonnee ;

    DCA() {
    }
    DCA(String fichier, int ordre){
        this.fichier = fichier;
        this.Ordre = ordre ;
    }

    void Formules(){

      TP = (float)((float)MCAVPositife / (float)anormal);

            FN = (float) ((float)(MCAVNull - (float)normal)/(float)anormal) ;


            FP=(float)(((float)anormal-(float)MCAVPositife)/(float)MCAVNull);



            TN=(float)((float)MCAVNull/(float)normal);

             if(TN>1)TN=1;

            //DR=;

            //FAR= ;




           
            





            System.out.println("TP(true positive) = " + TP);
            System.out.println("FN (false negative) = " + FN);
             System.out.println("FP  = " + FP);
               System.out.println("TN  = " + TN);
                 System.out.println("DR  = " + DR);
                   System.out.println("FAR  = " + FAR);


    }



    void OrganierDonnees() {

        entrer = new Entrer[nbEx];
        //System.out.println("entrerDonnee.size() "+entrerDonnee.size());
        if (Ordre == Continu) {
            int i = 0;
            while (!entrerDonnee.isEmpty()) {
                entrer[i] = entrerDonnee.elementAt(0);
                entrerDonnee.removeElementAt(0);
                i++;
                System.out.println("entrerDonnee. = "+entrerDonnee.size());
            }
        } else if (Ordre == aleatoire) {
            int i = 0;
            while (!entrerDonnee.isEmpty()) {
                Random r = new Random();
                int index = r.nextInt(entrerDonnee.size());


                entrer[i] = entrerDonnee.elementAt(index);
                entrerDonnee.removeElementAt(index);
                i++;
                // //System.out.println("index = "+index);
            }

        }






    }

    void ChargerEntrer() {

 entrerDonnee = new Vector<Entrer>();
        // ouverture du fichier
        BufferedReader IN = null;
        try {
            IN = new BufferedReader(new FileReader(fichier));
        } catch (Exception e) {
            System.err.println("Erreur : " + e);
        }
        String ligne = null;

        // gestion des éventuelles erreurs
        try {
            //   ligne = IN.readLine();
            //  int i = 0;
            // nbEx = Integer.parseInt(ligne);
            //this.entrer = new Entrer[nbEx];

            while ((ligne = IN.readLine()) != null) {

                String[] champs = null;
                champs = ligne.split(";");
                String[] meriem = champs[0].split(",");
                Antigene antigene = new Antigene(meriem[0], meriem[1], meriem[2]);

                meriem = champs[1].split(",");
                Safe safe = new Safe(meriem[0], meriem[1], meriem[2]);

                meriem = champs[2].split(",");
                Danger danger = new Danger(meriem[0], meriem[1]);

                meriem = champs[3].split(",");
                PAMP pamp = new PAMP(meriem[0], meriem[1], meriem[2], meriem[3], meriem[4]);
                Entrer entrer = new Entrer(antigene, safe, danger, pamp);
                if (champs[4].trim().equals("normal")) {
                    normal++;
                } else if (champs[4].trim().equals("Anormal")) {
                    anormal++;
                }
                // this.entrer[i] = entrer;
                entrerDonnee.addElement(entrer);
                // i++;
            }// fin while
            nbEx = entrerDonnee.size();

            OrganierDonnees();

            System.out.println("normal = " + normal + " , Anormal= " + anormal);
            //System.out.println("nbEx = "+nbEx);


        } catch (Exception e) {
            System.err.println("Erreur : " + e);
        }
    }

    void Creer100DCs() {
 dcs100 = new Vector<DCs>();
        for (int i = 0; i < 100; i++) {
            DCs dcs = new DCs();
            dcs100.addElement(dcs);
        }
    }

    void RemplacerDCs() {
//retirer et sauvegarder
        DCs dcs = new DCs();
        this.dcs100.addElement(dcs);
    }

    void Selection10DCs() {
//retirer et sauvegarder

        for (int i = 0; i < 10; i++) {
            Random r = new Random();
            int pp = r.nextInt(99);
            dcs10[i] = this.dcs100.elementAt(pp);
        }
    }

    void MiseAJour() {
        System.out.println("this.dcs100  "+this.dcs100.size());
        for (int i = 0; i < this.entrer.length; i++) {
            Entrer entrer = this.entrer[i];
            this.dcs10 = new DCs[10];
            //selectionner 10 Dcs
            for (int v = 0; v < 10; v++) {
                Random r = new Random();
                int pp = r.nextInt(99);

                dcs10[v] = this.dcs100.elementAt(pp);
            }

            for (int j = 0; j < 10; j++) {
                DCs dcs = this.dcs10[j];
                double CSM = 2 * (entrer.pamp.a1 + entrer.pamp.a2 + entrer.pamp.a3 + entrer.pamp.a4 + entrer.pamp.a5) + (entrer.danger.d1 + entrer.danger.d2) + 2 * (entrer.safe.s1 + entrer.safe.s2 + entrer.safe.s3);
                double Semi = 2 * (entrer.safe.s1 + entrer.safe.s2 + entrer.safe.s3);
                double Mat = 2 * (entrer.pamp.a1 + entrer.pamp.a2 + entrer.pamp.a3 + entrer.pamp.a4 + entrer.pamp.a5) + (entrer.danger.d1 + entrer.danger.d2) - 2 * (entrer.safe.s1 + entrer.safe.s2 + entrer.safe.s3);
                if (CSM >= dcs.SeulM) {

                    this.dcs100.remove(dcs);

                    if (Semi > Mat) {

                        dcs.Context = 0;
                        dcs.Etat = "semi-mature";
                    } else {
                        dcs.Context = 1;
                        dcs.Etat = "mature";
                    }
                    entrer.dcs.addElement(dcs);
                    this.RemplacerDCs();
                }
            }
           
          //  System.out.println(i);
        }
  this.dcs100=null;
    }

     public void Run() {

        int nbIteration = 1;

        float MaxTP=0;
            System.out.println("debutttttttttttttt ");
 for (int b = 0; b < nbIteration; b++) {



            System.out.println("Chargement des entrées");
            ChargerEntrer();




            //System.out.println("Initialisation");
            Creer100DCs();
            System.out.println("Mise a jour");
            MiseAJour();
            System.out.println("Analyse");
            

            for (int i = 0; i < entrer.length; i++) {
                Entrer entrer = this.entrer[i];
                for (int j = 0; j < entrer.dcs.size(); j++) {

                    DCs dcs = entrer.dcs.elementAt(j);

                    if (dcs.Etat.equals("mature")) {
                        entrer.Mature++;
                    } else {
                        entrer.Semi_Mature++;
                    }
                }
                if (entrer.Mature > entrer.Semi_Mature) {
                    entrer.MCAV = 1;
                    MCAVPositife++;
                } else {
                    entrer.MCAV = 0;

                    MCAVNull++;
                }
            }

            System.out.println("i= " + b + " , MCAVP(anormal)= " + MCAVPositife + " ,MCAVN(normal)= " + MCAVNull);
 System.out.println("dca.anormal= " + anormal + " , dca.normal= " + normal);







 ///////////////////////////////////////

          Formules( );


  /////////////////////////////////









           if(MaxTP<TP)
            MaxTP=TP;








            System.out.println("dca.maxTP = "+maxTP);

        }
            System.out.println("dca.MaxTP = "+MaxTP);

        System.out.println("finnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
    }








    public static void main(String arg[]) {

        int nbIteration =1;

        float MaxTP=0;
            System.out.println("debutttttttttttttt ");
 for (int b = 0; b < nbIteration; b++) {
            DCA dca = new DCA();
    

            System.out.println("Chargement des entrées");
            dca.ChargerEntrer();




            //System.out.println("Initialisation");
            dca.Creer100DCs();
            System.out.println("Mise a jour");
            dca.MiseAJour();
            System.out.println("Analyse");
            

            for (int i = 0; i < dca.entrer.length; i++) {
                Entrer entrer = dca.entrer[i];
                for (int j = 0; j < entrer.dcs.size(); j++) {

                    DCs dcs = entrer.dcs.elementAt(j);

                    if (dcs.Etat.equals("mature")) {
                        entrer.Mature++;
                    } else {
                        entrer.Semi_Mature++;
                    }
                }
                if (entrer.Mature > entrer.Semi_Mature) {
                    entrer.MCAV = 1;
                    dca.MCAVPositife++;
                } else {
                    entrer.MCAV = 0;

                    dca.MCAVNull++;
                }
            }

            System.out.println("i= " + b + " , MCAVP(anormal)= " + dca.MCAVPositife + " ,MCAVN(normal)= " + dca.MCAVNull);
 System.out.println("dca.anormal= " + dca.anormal + " , dca.normal= " + dca.normal);


dca.Formules();

           if(MaxTP<dca.TP)
            dca.maxTP=dca.TP;






            //System.out.println("TP(true positive) = " + dca.TP);
           // System.out.println("FN (false negative) = " + dca.FN);
            System.out.println("dca.maxTP = "+dca.maxTP);

        }
         
          
        System.out.println("finnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
        
    }
}
