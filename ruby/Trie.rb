# Simple Trie structure for quickly finding prefix matches
# against a string
class Trie

  # Create the main hash
  def initialize()
    @trie = Hash.new()
  end

  # Check of prefix exists within string Trie setup to handle
  def exists?(s)
    final = true
    index = @trie
    s.each_char do |c|
      current = c
      index = index[current]
      if index == nil
        return false
      end
    end
    return true
  end

  # Build Trie based on string
  def prepare(s)
    index = @trie
    s.each_char do |c|
      previous_index = index
      index = index[c]
      if index == nil
        previous_index[c] = Hash.new()
        index = previous_index[c]
      end
    end
  end
end
