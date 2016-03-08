package at.ac.tuwien.ifs.grepsearchengine.textpreprocessing.tokenizer

import java.io.FileReader

import at.ac.tuwien.ifs.grepsearchengine.model.PreProcessableInput
import at.ac.tuwien.ifs.grepsearchengine.model.index.{DocumentVector, Term, TopicVector}
import at.ac.tuwien.ifs.grepsearchengine.model.input.{Document, Topic}
import at.ac.tuwien.ifs.grepsearchengine.textpreprocessing.Component

import scala.io.Source

/**
 * Created by aldo on 26/02/16.
 */
class WhiteSpaceTokeniser extends Component{

  override def preProcess(input: PreProcessableInput):PreProcessableInput = input match {
    case d:Document => tokenize(d)
    case d:Topic => tokenize(d)
    case _ => throw new Exception("non")
  }

  def tokenize(d:Document):DocumentVector= {
    val s = Source.fromFile(d.filePath)
    val txt =  s.getLines().mkString(" ")
    s.close()
    val ts = txt.split("\\s+")

    val terms:List[Term] = ts.filter(_!=null).toList.groupBy(w => w).map(e => new Term(e._1, e._2.size.toDouble)).toList
    new DocumentVector(d.id, terms)
  }

  def tokenize(t:Topic):TopicVector = {
    val s = Source.fromFile(t.filePath)
    val txt = s.getLines().mkString(" ")
    s.close()
    val ts = txt.split("\\s+")
    val terms:List[Term] = ts.filter(_!=null).toList.groupBy(w => w).map(e => new Term(e._1, e._2.size.toDouble)).toList
    new TopicVector(t.id, terms)
  }

}
