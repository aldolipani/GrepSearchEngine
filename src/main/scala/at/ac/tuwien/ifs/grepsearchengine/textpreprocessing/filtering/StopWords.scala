package at.ac.tuwien.ifs.grepsearchengine.textpreprocessing.filtering

import at.ac.tuwien.ifs.grepsearchengine.model.{TermsVector, PreProcessableInput}
import at.ac.tuwien.ifs.grepsearchengine.model.index.DocumentVector
import at.ac.tuwien.ifs.grepsearchengine.textpreprocessing.Component

import scala.io.Source

/**
 * Created by aldo on 29/02/16.
 */
class StopWords extends Component{

  lazy val sws:Set[String] = Source.fromURL(getClass.getResource("/stopwords/terrier.terms")).getLines().toSet

  override def preProcess(input: PreProcessableInput):PreProcessableInput = input match {
    case tl:TermsVector => filter(tl)
    case _ => throw new Exception("non")
  }

  def filter(tl:TermsVector):TermsVector = {
    def collapse(term:String):String =
      if(sws.contains(term)) null else term

    tl.apply(collapse)
  }

}
