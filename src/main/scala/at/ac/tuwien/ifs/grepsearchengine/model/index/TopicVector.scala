package at.ac.tuwien.ifs.grepsearchengine.model.index

import at.ac.tuwien.ifs.grepsearchengine.model.TermsVector


/**
 * Created by aldo on 29/02/16.
 */
class TopicVector(val id:String, override val terms:List[Term]) extends TermsVector(id, terms)
