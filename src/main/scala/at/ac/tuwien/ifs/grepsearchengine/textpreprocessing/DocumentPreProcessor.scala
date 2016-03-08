package at.ac.tuwien.ifs.grepsearchengine.textpreprocessing

import at.ac.tuwien.ifs.grepsearchengine.textpreprocessing.filtering.StopWords
import at.ac.tuwien.ifs.grepsearchengine.textpreprocessing.stemmer.PorterStemmer
import at.ac.tuwien.ifs.grepsearchengine.textpreprocessing.tokenizer.EnglishTokeniser

/**
 * Created by aldo on 26/02/16.
 */
class DocumentPreProcessor(components:List[Component] = List(new EnglishTokeniser, new PorterStemmer, new StopWords)) extends TextPreProcessor(components)
