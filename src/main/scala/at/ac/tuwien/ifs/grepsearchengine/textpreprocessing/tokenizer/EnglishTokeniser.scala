package at.ac.tuwien.ifs.grepsearchengine.textpreprocessing.tokenizer

import java.io.{Reader, FileReader}

import at.ac.tuwien.ifs.grepsearchengine.model.input.{Topic, Document}
import at.ac.tuwien.ifs.grepsearchengine.model.index.{TopicVector, Term, DocumentVector}
import at.ac.tuwien.ifs.grepsearchengine.model.{TermsVector, PreProcessableInput}
import at.ac.tuwien.ifs.grepsearchengine.textpreprocessing.Component
import scala.io.Source

/**
 * Created by aldo on 26/02/16.
 */
class EnglishTokeniser extends Component{

  override def preProcess(input: PreProcessableInput):PreProcessableInput = input match {
    case d:Document => tokenize(d)
    case d:Topic => tokenize(d)
    case _ => throw new Exception("non")
  }

  def tokenize(d:Document):DocumentVector= {
    val r = new FileReader(d.filePath)
    val et = new org.terrier.indexing.tokenisation.EnglishTokeniser()
    val ts = et.tokenise(r)
    r.close()
    import scala.collection.JavaConversions._
    val terms:List[Term] = ts.withFilter(_!=null).toList.groupBy(w => w).map(e => new Term(e._1, e._2.size.toDouble)).toList
    new DocumentVector(d.id, terms)
  }

  def tokenize(t:Topic):TopicVector = {
    val r = new FileReader(t.filePath)
    val et = new org.terrier.indexing.tokenisation.EnglishTokeniser()
    val ts = et.tokenise(r)
    r.close()
    import scala.collection.JavaConversions._
    val terms:List[Term] = ts.withFilter(_!=null).toList.groupBy(w => w).map(e => new Term(e._1, e._2.size.toDouble)).toList
    new TopicVector(t.id, terms)
  }

}
