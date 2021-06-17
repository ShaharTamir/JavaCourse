public class TextList {

    private static final String EMPTY_STRING = new String();
    private WordNode _head;

    /**
     * Default C'tor - construct a new empty list
     */
    public TextList()
    {
        _head = new WordNode();
    }

    /**
     * C'tor - construct a new list containing <tt>text</tt> words sorted lexicographically
     * @param text text to insert to list
     */
    public TextList(String text)
    {
        this();

        int length = text.length();

        if(length > 0)
        {
            int j = 0;
            String word;
            
            for(int i = 0; i < length && j != -1; i = j + 1)
            {
                j = text.indexOf(' ', i);
                if(j == -1) // means no more spaces
                    word = text.substring(i);
                else
                    word = text.substring(i, j); // separate each single word from the text
                
                SortInsert(word);
            }
        }
    }

    /**
     * Add a single word to the list<p>
     * if given more that one word - only the first word is inserted.
     * @param word word to insert to list
     */
    public void addToData(String word)
    {
        if(word.length() > 0)
        {
            int i = word.indexOf(' ');
            
            if(i == -1) // user inserted only 1 word 
                SortInsert(word);
            else // insert only the first word from user 'word' String
                SortInsert(word.substring(0, i));
        }
    }

    /**
     * This method returns the number of words in the list
     * @return number of words on the list
     */
    public int howManyWords()
    {
        if(_head.getWord().equals(EMPTY_STRING))
            return 0;

        WordNode iter = _head.getNext();
        int count = 1;

        while(null != iter) // count all the words on the list
        {
            ++count;
            iter = iter.getNext();
        }

        return count;
    }

    /**
     * This method returns the number of different words in the list
     * @return the number of different words in the list
     */
    public int howManyDifferentWords()
    {
        if(_head.getWord().equals(EMPTY_STRING))
            return 0;

        WordNode iter = _head;
        int count = 1;

        while(null != iter.getNext())
        {
            // count the words that are not equal
            if(false == iter.getWord().equals(iter.getNext().getWord()))
                ++count;
            
            iter = iter.getNext();
        }

        return count;
    }

    /**
     * This method returns the most frequent word on the list
     * @return the most frequent word on the list
     */
    public String mostFrequentWord()
    {
        if(_head.getWord().equals(EMPTY_STRING))
            return new String();

        String currMostFreq = _head.getWord();
        WordNode iter = _head;
        int count = 1; // each word on list appear at least once.
        int max = 1;

        while(null != iter.getNext())
        {
            // count repetitions of word
            if(iter.getWord().equals(iter.getNext().getWord()))
                ++count;
            else
                count = 1; // reset count
            
            if(count > max)
            {
                // update the most frequent word and how many times repeated.
                currMostFreq = iter.getWord();
                max = count;
            }

            iter = iter.getNext();
        }

        return currMostFreq;
    }

    /**
     * This method returns the number of words starting with <tt>letter</tt>
     * @param letter The starting letter to look for in words on the list
     * @return the number of words on the list that starts with letter <tt>letter</tt>
     */
    public int howManyStarting(char letter)
    {
        if(_head.getWord().equals(EMPTY_STRING))
            return 0;

        WordNode iter = _head;

        if(iter.getWord().charAt(0) > letter) 
        // ascii compare - first letter on list is bigger than given letter, means doesn't appear on list.
            return 0;

        while(null != iter && iter.getWord().charAt(0) < letter)
            iter = iter.getNext();

        if(null == iter) // first letter doesn't exist on the list
            return 0;
        
        int count = 0;
        while(null != iter && iter.getWord().charAt(0) == letter)
        {
            // count the number of words starting with letter
            ++count;
            iter = iter.getNext();
        }

        return count;
    }

    /**
     * This method returns the most frequent starting letter of words on the list
     * @return the most frequent starting letter
     */
    public char mostFrequentStartingLetter()
    {
        if(_head.getWord().equals(EMPTY_STRING))
            return 0;
        
        WordNode iter = _head;
        char mostFreqLetter = _head.getWord().charAt(0);
        int count = 1;
        int max = 1;

        while(null != iter.getNext())
        {
            // count repetitions of first letter
            if(iter.getWord().charAt(0) == iter.getNext().getWord().charAt(0))
                ++count;
            else
                count = 1; // reset count
            
            if(count > max)
            {
                // update the maximum count and the most frequent letter
                mostFreqLetter = iter.getWord().charAt(0);
                max = count;
            }

            iter = iter.getNext();
        } // while

        return mostFreqLetter;
    }

    /**
     * This method return a string of the words on the list by lexicographical order<p>
     * Each word has a number next to it representing how many times this word<p>
     * is shown on the list.
     * @return a string containing the words on the list and 
     * how many times each word is repeated on the list.<p>
     * if list is empty return an empty string.
     */
    public String toString()
    {
        String retStr = new String();

        if(!_head.getWord().equals(EMPTY_STRING))
        {
            WordNode iter = _head;
            int count = 1;

            while(null != iter)
            {
                // count word repetitions
                if(null != iter.getNext() && iter.getWord().equals(iter.getNext().getWord()))
                {
                    ++count;
                }
                else // add the word to the string and the count of repetitions
                {
                    retStr += iter.getWord() + "\t" + count + "\n";
                    count = 1; // reset count
                }

                iter = iter.getNext();
            }
        }
        
        return retStr;
    }

    /************************
    *     Private Methods   * 
    ************************/

    /**
     * This method add a single word to the list in a lexicographically order.
     * parameter - word - a word to add to the list.
     */
    private void SortInsert(String word)
    {
        WordNode iter = _head;
        int compareResult = 0;

        if(!iter.getWord().equals(EMPTY_STRING))
        {
            compareResult = word.compareTo(iter.getWord());
            
            while(compareResult > 0) // while word is lexicographically after iter word
            {
                if(null == iter.getNext())
                {
                    // means word has the lexicographical value and should be last on the list
                    iter.setNext(new WordNode(word));
                    return;
                }

                iter = iter.getNext();
                compareResult = word.compareTo(iter.getWord());
            }

            insertBefore(iter, word);
        }
        else // currWord == null - the list is empty.
        {
            iter.setWord(word);
        }
    }

    /**
     * This method insert a new WordNode containing 'word' parameter before given 'curr' WordNode.
    */
    private void insertBefore(WordNode curr, String word)
    {
        /* 
            1. nodeBeforeCurrent->current->nodeAfterCurrent - starting state
            2. nodeBeforeCurrent->current->newCopyOfCurr->nodeAfterCurrent - Line 285
            3. nodeBeforeCurrent->currWithNewWord->newCopyOfCurr->nodeAfterCurrent - Line 286
        */
        curr.setNext(new WordNode(curr.getWord(), curr.getNext())); 
        curr.setWord(word);
    }

} // end of class TextList
