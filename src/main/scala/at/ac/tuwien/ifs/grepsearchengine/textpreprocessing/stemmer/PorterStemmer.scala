package at.ac.tuwien.ifs.grepsearchengine.textpreprocessing.stemmer

import at.ac.tuwien.ifs.grepsearchengine.model.{TermsVector, PreProcessableInput}
import at.ac.tuwien.ifs.grepsearchengine.model.index.DocumentVector
import at.ac.tuwien.ifs.grepsearchengine.textpreprocessing.Component

/**
 * Created by aldo on 26/02/16.
 */
class PorterStemmer extends Component{

  override def preProcess(input: PreProcessableInput):PreProcessableInput = input match {
    case tl:DocumentVector => stem(tl)
    case _ => throw new Exception("non")
  }

  def stem(tl:DocumentVector):DocumentVector = {
    val ps = new org.terrier.terms.PorterStemmer
    tl.apply(ps.stem)
  }

}
