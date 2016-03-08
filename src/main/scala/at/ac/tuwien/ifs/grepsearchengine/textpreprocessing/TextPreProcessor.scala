package at.ac.tuwien.ifs.grepsearchengine.textpreprocessing

import at.ac.tuwien.ifs.grepsearchengine.model.input.{Topic, Document}
import at.ac.tuwien.ifs.grepsearchengine.model.{TermsVector, PreProcessableInput}
import at.ac.tuwien.ifs.grepsearchengine.model.index.{TopicVector, DocumentVector}
import at.ac.tuwien.ifs.grepsearchengine.textpreprocessing.filtering.StopWords
import at.ac.tuwien.ifs.grepsearchengine.textpreprocessing.stemmer.PorterStemmer
import at.ac.tuwien.ifs.grepsearchengine.textpreprocessing.tokenizer.EnglishTokeniser

/**
 * Created by aldo on 26/02/16.
 */
class TextPreProcessor(components:List[Component] = List(new EnglishTokeniser, new PorterStemmer, new StopWords)) {

  def preProcess(input:Document):DocumentVector = {
    components.foldLeft[PreProcessableInput](input)((i, pp) => pp.preProcess(i)) match {
      case tl:DocumentVector => tl
      case _ => throw new Exception("non")
    }
  }

  def preProcess(input:Topic):TopicVector = {
    components.foldLeft[PreProcessableInput](input)((i, pp) => pp.preProcess(i)) match {
      case tl:TopicVector => tl
      case _ => throw new Exception("non")
    }
  }


}

object TextPreProcessor {

}