import java.sql.Date;
import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class main {

	public static void main(String[] args) { MongoClient mongoClient = null;

Date d=new Date(18, 3, 4);
Date d1=new Date(8, 2, 2);
Date d2=new Date(7, 4, 4);
Date d3=new Date(7, 5, 6);
Date d4=new Date(6, 11, 6);
peinture p1=new peinture("couchee de soleil",d,"moyenage",120000,true,2);

peinture p2=new peinture("la nuit etoilée",d1,"moyenage",200000,true,3);
peinture p3=new peinture("coline ténébreuse",d,"moyenage",125000,true,2);
sculpture s1=new sculpture("le musicien",d3,"histoire",125000,true,100,80,"pierre");
sculpture s2=new sculpture("venus de milo",d4,"histoire",140000,true,400,60,"pierre");

//creation d'une gallerie
ArrayList<oeuvre> lo = new ArrayList<oeuvre>();
Galerie g2=new Galerie(lo, "lara","hlif",0);
Galerie g1=new Galerie(lo, "emna","hlif",0);
g1.ajouterpeinture(p1);
g1.ajouterpeinture(p2);
g1.ajouterpeinture(p3);
g1.ajoutersculpture(s1);
g1.ajoutersculpture(s2);
System.out.println(g1.chercheroeuvre("musicien"));
g2.ajouterpeinture(p1);
System.out
//connexion a la base de données
.println("-_-_-Operations CRUD -_-_-");
mongoClient = new MongoClient();
MongoDatabase db = mongoClient.getDatabase("gallerie");

MongoCollection<Document> collection = db
.getCollection("peinture");
MongoCollection<Document> collection2 = db
    .getCollection("sculpture");
// Show all documents in the collection
MongoCollection<Document> collection3 = db
.getCollection("gallerie");

//cr.showAllDocuments( collection3);
//isertion

boolean status3 = g1.insertIntoCollection(collection3, g1);
boolean status2 = g1.insertIntoCollectionS(collection2, s1);
boolean status21 = g1.insertIntoCollectionS(collection2, s2);
boolean status = g1.insertIntoCollectionP(collection, p1);
boolean statuss = g1.insertIntoCollectionP(collection, p2);
boolean statusss = g1.insertIntoCollectionP(collection, p3);

System.out.println("Status of Insert: " + status);

//Show all documents in the collection before deletes
System.out.println("-----[ after insert]-----");
g1.showAllDocuments(collection3);
g1.showAllDocuments(collection2);
g1.showAllDocuments(collection);

//g1.findOneAndDeleteCollectionG(collection3, "emna");


//System.out.println("-----[ before delete]-----");

//g1.showAllDocuments(collection3);
//System.out.println("-----[ after delete]-----");
//g1.findOneAndDeleteCollectionG(collection3, "emna");
System.out.println("-----[ before update]-----");

g1.showAllDocuments(collection3);
System.out.println("-----[ after update]-----");
g1.updateCollection(collection3, g2);


//g1.showAllDocuments(collection3);




g1.affichageparallele(lo);



	}

}
