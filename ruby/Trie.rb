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
      tindex = index.dup
      r = 0
      tindex = tindex[c]
      if tindex == nil
        r += 1
      end

      jindex = index.dup
      current = c.swapcase
      jindex = jindex[current]
      if jindex == nil
        r += 2
      end

      if r == 1
        index = jindex
      elsif r == 2
        index = tindex
      else
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
