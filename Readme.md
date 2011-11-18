# Merge prefixes with matching words!

These implementations will do a filtered merge, given two lists:

* One of **sorted** words
* Oone of **sorted** shorter-length prefixes

Elements in either list will only make it into the final merged
list if:

* A word has a matching prefix
* A prefix has no matching word

They will be added in order of their appearance so they will be
automatically sorted thusly.

# How exactly?

First a [Trie](http://en.wikipedia.org/wiki/Trie) was constructed to
ease evaluation of each source word. From here:

* Each prefix is tried
  - If the first prefix matched then we 'cache' (0 index)
     + Remove it from the prefix list
     + continue its use until a new match is found
  - If, however the matching prefix isn't at 0 index
  	 + append all leading prefixes to the final list
     + remove them from the prefix list
* Do a final check for trailing prefixes in the prefix list
  - Add ones we don't already have

# Languages

Currently written in:

* Java
* Ruby

With their implementations residing in their respective directories.

# Author

Jon Phenow

http://jphenow.com

Contact me if you have any questions or concerns.
