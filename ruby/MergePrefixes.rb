# Little application that uses a Trie datatype to
# merge a list of source words with prefixes:
#   Words with accepting prefixes
require File.expand_path('../Trie.rb', __FILE__)

sources = ['bash', 'cplusplus', 'java',  'javascript', 'php', 'python', 'ruby']
prefixes = ['ba', 'bu', 'jav', 'ph', 'ru']

final = Array.new

sources.each do |source|
  source_dump = Array.new
  t = Trie.new
  t.prepare source
  prefixes.each do |pre|
    if t.exists? pre
      source_dump.each do |g|
        trash = Trie.new
        trash.prepare final[-1]
        if g != pre and not trash.exists? g
          final << g
          prefixes.delete g
        end
      end
      final << source
      break
    else
      final.each do |f|
        trash = Trie.new
        trash.prepare f
        if trash.exists? pre
          prefixes.delete pre
        else
          source_dump << pre
        end
      end
    end
  end
end

puts final
