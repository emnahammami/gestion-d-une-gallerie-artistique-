import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.codehaus.jackson.annotate.JsonProperty;

import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.ObjectMapper;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
@JsonPropertyOrder({"nomorganisateur","adesse","nombreoeuvre","lo"})
public class Galerie {


private ArrayList<oeuvre> listeoeuvre = new ArrayList<oeuvre>(); // Create an ArrayList object
private String nomOrganisateur;
private String adresse;
private int nombreoeuvre;
private ArrayList<oeuvre> NULL;





//constructeur
public Galerie(ArrayList<oeuvre> listeoeuvre, String nomOrganisateur, String adresse, int nombreoeuvre) {
	super();
	this.listeoeuvre = listeoeuvre;
	this.nomOrganisateur = nomOrganisateur;
	this.adresse = adresse;
	this.nombreoeuvre = nombreoeuvre;
}




@JsonProperty("lo")
public ArrayList<oeuvre> getListeoeuvre() {
	return listeoeuvre;
}





public void setListeoeuvre(ArrayList<oeuvre> listeoeuvre) {
	this.listeoeuvre = listeoeuvre;
}




@JsonProperty("nomorganisateur")
public String getNomOrganisateur() {
	return nomOrganisateur;
}





public void setNomOrganisateur(String nomOrganisateur) {
	this.nomOrganisateur = nomOrganisateur;
}




@JsonProperty("adresse")
public String getAdresse() {
	return adresse;
}





public void setAdresse(String adresse) {
	this.adresse = adresse;
}




@JsonProperty("nombreoeuvre")
public int getNombreoeuvre() {
	return nombreoeuvre;
}





public void setNombreoeuvre(int nombreoeuvre) {
	this.nombreoeuvre = nombreoeuvre;
}



//la methode qui cherche une oeuvre a partir d'un mot cle

public ArrayList<oeuvre> chercheroeuvre(String sbstr)
{ ArrayList<oeuvre> lo = new ArrayList<oeuvre>();
	
try {
for (int i = 0; i < listeoeuvre.size(); i++) {
   
    if(listeoeuvre.get(i).getNom().indexOf(sbstr)>=0) {
    	lo.add((sculpture) listeoeuvre.get(i));
    }
    
  }return lo;}

catch (Exception e) {return NULL;}


}




//la méthode qui retourne la liste des sculptures
public ArrayList<sculpture> retournersculpture()
{ ArrayList<sculpture> lp = new ArrayList<sculpture>();
	for (int i = 0; i < listeoeuvre.size(); i++) {
   
    if(listeoeuvre.get(i) instanceof sculpture) {
    	lp.add((sculpture) listeoeuvre.get(i));
    }
    
  }return lp;}



public  void ajouterpeinture(peinture p)
{listeoeuvre.add(p);
nombreoeuvre++;}
public  void ajoutersculpture(sculpture s)
{listeoeuvre.add(s);
nombreoeuvre++;}
//methode qui retourne la liste des peintures
public ArrayList<peinture> retournerpeintutre()
{ ArrayList<peinture> lp = new ArrayList<peinture>();
	for (int i = 0; i < listeoeuvre.size(); i++) {
   
    if(listeoeuvre.get(i) instanceof peinture) {
    	lp.add((peinture) listeoeuvre.get(i));
    }
    
  }return lp;}
//retourne la liste des oeuvres ayant un prix compris entre p1 et p2
public ArrayList<oeuvre> retournerlalistedesoeuvrebornee(int a,int b)
{ArrayList<oeuvre> lob = new ArrayList<oeuvre>();
for (int i = 0; i < listeoeuvre.size(); i++) {
 if(   (listeoeuvre.get(i).getPrix()<=b) &&(listeoeuvre.get(i).getPrix()<=a))
 {  
    lob.add(listeoeuvre.get(i));
  }
}return lob;}

//la méthode qui utilise les threads pour afficher le nom de l'oeuvre et son prix
public void affichageparallele(ArrayList<oeuvre> lo) {
	
	for (int i = 0; i < lo.size(); i++) {
		thread t=new thread(lo.get(i));

		affichage t1=new affichage(lo.get(i));

		t.start();
		t1.start();
		
		}
	
	
	
	
}


public static boolean insertIntoCollection(
	      final MongoCollection<Document> collection, final Galerie item) {
	    // Use Jackson to convert Object to JSON String
	    ObjectMapper mapper = new ObjectMapper();
	    String jsonString;
	    boolean status = true;

	    try {
	      jsonString = mapper.writeValueAsString(item);
	      // Insert JSON into MongoDB
	      System.out.println(String.format("Item #%s: %s", item.getClass(),
	          jsonString));
	      Document doc = Document.parse(jsonString);
	      collection.insertOne(doc);
	    } catch (MongoWriteException mwe) {
	      status = false;
	    } catch (IOException e) {
	      status = false;
	    }
	    return status;
	  }

	  public static boolean replaceUpsertCollection(
	      final MongoCollection<Document> collection, final Galerie item) {
	    boolean status = true;
	    ObjectMapper mapper = new ObjectMapper();
	    String jsonString;

	    try {
	      jsonString = mapper.writeValueAsString(item);
	      // Insert JSON into MongoDB
	      System.out.println(String.format("Item #%s: %s", item.getAdresse(),
	          jsonString));
	      BasicDBObject query = new BasicDBObject();
	      query.append("_id", item.getAdresse());
	      Document doc = Document.parse(jsonString);
	      UpdateResult result = collection.replaceOne(query, doc,
	          (new UpdateOptions()).upsert(true));
	      System.out.println("Replace Matched Count....: "
	          + result.getMatchedCount());
	      System.out.println("Replace Modified Count...: "
	          + result.getModifiedCount());
	    } catch (MongoWriteException mwe) {
	      status = false;
	    } catch (IOException e) {
	      status = false;
	    }

	    return status;
	  }

	  public static boolean updateCollection(
	      final MongoCollection<Document> collection, final Galerie item) {
	    boolean status = true;
	    ObjectMapper mapper = new ObjectMapper();
	    String jsonString;

	    try {
	      jsonString = mapper.writeValueAsString(item);
	      // update/upsert using JSON into MongoDB
	      System.out.println(String.format("Item #%s: %s", item.getAdresse(),
	          jsonString));
	      BasicDBObject query = new BasicDBObject();
	      query.append("adresse", item.getAdresse());
	      BasicDBObject doc = BasicDBObject.parse(jsonString);
	      Bson newDocument = new Document("$set", doc);
	      UpdateResult result = collection.updateOne(query, newDocument,
	          (new UpdateOptions()).upsert(true));
	      System.out.println("Update Matched Count....: "
	          + result.getMatchedCount());
	      System.out.println("Update Modified Count...: "
	          + result.getModifiedCount());
	    } catch (MongoWriteException mwe) {
	      status = false;
	    } catch (IOException e) {
	      status = false;
	    }

	    return status;
	  }

	  public static Document findAndUpdateCollection(
	      final MongoCollection<Document> collection, final Galerie item) {
	    ObjectMapper mapper = new ObjectMapper();
	    Document resultDocument = null;
	    String jsonString;

	    try {
	      // findOneAndUpdate using JSON into MongoDB
	      jsonString = mapper.writeValueAsString(item);
	      System.out.println(String.format("Item #%s: %s", item.getAdresse(),
	          jsonString));
	      BasicDBObject query = new BasicDBObject();
	      query.append("adresse", item.getAdresse());
	      BasicDBObject doc = BasicDBObject.parse(jsonString);
	      Bson newDocument = new Document("$set", doc);
	      resultDocument = collection.findOneAndUpdate(query,
	          newDocument, (new FindOneAndUpdateOptions()).upsert(true));
	    } catch (IOException e) {
	      e.printStackTrace();
	    }

	    return resultDocument;
	  }

	  public static Document findOneAndDeleteCollectionG(
	      final MongoCollection<Document> collection, final String id) {
	    Document resultDocument = null;

	    // findOneAndDelete from MongoDB
	    System.out.println(
	      "Using findOneAndDeleteCollection to delete ID: " + id);
	    BasicDBObject query = new BasicDBObject();
	    query.append("nomorganisateur", id);
	    resultDocument = collection.findOneAndDelete(query);

	    return resultDocument;
	  }

	  public static DeleteResult deleteOneFromCollection(
	      final MongoCollection<Document> collection, final String id) {
	    DeleteResult resultDocument = null;

	    // findOneAndDelete from MongoDB
	    System.out.println(
	        "Using deleteOneFromCollection to delete ID: " + id);
	    BasicDBObject query = new BasicDBObject();
	    query.append("nomorganisateur", id);
	    resultDocument = collection.deleteOne(query);

	    return resultDocument;
	  }

	  public static void showDocumentByID(
	      final MongoCollection<Document> collection, final String id) {
	    BasicDBObject query = new BasicDBObject();
	    query.append("_id", id);

	    for (Document doc : collection.find(query)) {
	      System.out.println(doc.toJson());
	    }
	  }

	  public static void showAllDocuments(
	      final MongoCollection<Document> collection) {
	    System.out
	        .println("----[All Items in the Inventory Collection]----");
	    for (Document doc : collection.find()) {
	      System.out.println(doc.toJson());
	    }
	  }

	  public static void showAllDocs(final DBCollection collection) {
	    DBCursor cursor = (DBCursor) collection.find().iterator();
	    try {
	      while (cursor.hasNext()) {
	        System.out.println(cursor.next().toString());
	      }
	    } finally {
	      cursor.close();
	    }
	  }
	  public static boolean insertIntoCollectionS(
		      final MongoCollection<Document> collection, final sculpture item) {
		    // Use Jackson to convert Object to JSON String
		    ObjectMapper mapper = new ObjectMapper();
		    String jsonString;
		    boolean status = true;

		    try {
		      jsonString = mapper.writeValueAsString(item);
		      // Insert JSON into MongoDB
		      System.out.println(String.format("Item #%s: %s", item.getClass(),
		          jsonString));
		      Document doc = Document.parse(jsonString);
		      collection.insertOne(doc);
		    } catch (MongoWriteException mwe) {
		      status = false;
		    } catch (IOException e) {
		      status = false;
		    }
		    return status;
		  }
	  public static Document findOneAndDeleteCollectionS(
		      final MongoCollection<Document> collection, final String id) {
		    Document resultDocument = null;

		    // findOneAndDelete from MongoDB
		    System.out.println(
		      "Using findOneAndDeleteCollection to delete ID: " + id);
		    BasicDBObject query = new BasicDBObject();
		    query.append("_id", id);
		    resultDocument = collection.findOneAndDelete(query);

		    return resultDocument;
		  }

	  
	  public static boolean insertIntoCollectionP(
		      final MongoCollection<Document> collection, final peinture item) {
		    // Use Jackson to convert Object to JSON String
		    ObjectMapper mapper = new ObjectMapper();
		    String jsonString;
		    boolean status = true;

		    try {
		      jsonString = mapper.writeValueAsString(item);
		      // Insert JSON into MongoDB
		      System.out.println(String.format("Item #%s: %s", item.getClass(),
		          jsonString));
		      Document doc = Document.parse(jsonString);
		      collection.insertOne(doc);
		    } catch (MongoWriteException mwe) {
		      status = false;
		    } catch (IOException e) {
		      status = false;
		    }
		    return status;
		  }
	  

}
