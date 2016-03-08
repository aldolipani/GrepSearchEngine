package at.ac.tuwien.ifs.grepsearchengine.model.matching

import at.ac.tuwien.ifs.grepsearchengine.model.index.{DocumentVector, TopicVector}
import at.ac.tuwien.ifs.grepsearchengine.model.input.Document

/**
 * Created by aldo on 29/02/16.
 */
class ScoringModel {
  def score(tv:TopicVector, td:DocumentVector):Double = ???
}
