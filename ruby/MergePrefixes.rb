#!/usr/bin/env ruby
# Little application that uses a Trie datatype to
# merge a list of source words with prefixes:
#   Words with accepting prefixes
#   Prefixes without accepting words
require File.expand_path('../Trie.rb', __FILE__)

def fileList(dir, array)
  file = File.new(dir, "r")
  while (line = file.gets)
    array << line
  end
end

# Source strings
#sources = ['Bash', 'cplusplus', 'java',  'javascript', 'php', 'python', 'ruby']
sources = Array.new

# Prefix strings
#prefixes = ['ab', 'ba', 'bu', 'Jav', 'ph', 'ru', 'ze']
prefixes = Array.new

fileList("../lists/sources.list", sources)
fileList("../lists/prefixes.list", prefixes)

# Our ending list
final = Array.new

# Simple variable to 'cache' our last match and keep checking
# for similar matches before moving on to a new prefix
still_testing = nil

# Loop that cycles sources, sets up Trie for each source
# then checks for matching prefixes. On the first find of
# prefix we will stop, put the unmatching prefixes in the
# final list then continue checking our working prefix until
# we toss it. Continue this loop until we can print results
sources.each do |source|
  t = Trie.new
  t.prepare source
  if still_testing and t.exists? still_testing
    final << source
    next
  else
    prefixes.each do |pre|
      if t.exists? pre
        i = prefixes.index pre
        still_testing = pre
        if i > 0
          berid = prefixes[0..i-1]
          final = final | berid
          prefixes = prefixes - berid
        end
        prefixes.delete pre
        final << source
        break
      end
    end
  end
end

if prefixes.length > 0
  final = final | prefixes
end

puts final
