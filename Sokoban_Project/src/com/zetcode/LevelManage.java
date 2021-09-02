package com.zetcode;

import java.util.ArrayList;

public class LevelManage {
	
	ArrayList<String> levels =new ArrayList<String>();
	ArrayList<Integer> maxMove = new ArrayList<>();
	
	public LevelManage() {
		levels.add(0,   
	                 "    ######\n"
	               + "    ##+  #\n"
	               + "    ##$  #\n"
	               + "  ####  $##\n"
	               + "  ##  $ $ #\n"
	               + "#### # ## #   ######\n"
	               + "##   # ## #####  ..#\n"
	               + "## $  $          ..#\n"
	               + "###### ### #@##  ..#\n"
	               + "    ##     #########\n"
	               + "    ########\n");
	    maxMove.add(0, 260);  
		levels.add(1,
	              "                  \n"
	            + "     ###########\n"
	                + "     #@       ###\n"
	                + "     # $   $     #\n"
	                + "     ##   ##      #\n"
	                + "    ###   ##      # \n"
	                + "    #.#   ###    .##\n"
	                + "    #.$  +     #####\n"
	                + "    #.       $    ##\n"
	                + "    ################\n" );
	    maxMove.add(1,60); 
		
		levels.add(2,
	            
	               "   #######\n"
	                 + "   #  #  ###\n"
	                 + "   #     . ###\n"
	                 + "   #   $ # @ #\n"
	                 + "   #  # $ $  # \n"
	                 + "   ## # ..####\n"
	                 + "   #  #    #\n"
	                 + "   ## ###  ###\n"
	                 + "   #     $  +#\n"
	                 + "   #   ## .  #\n"
	                 + "   ##### #####\n");
		 maxMove.add(2, 300); 	      
	      levels.add(3,
	               "            \n"
	             + "    ########\n"
	                 + "    #@     #\n"
	                 + "    ###.$# #\n"
	                 + "    # .  $ #\n"
	                 + "    # $  . # \n"                   
	                 + "    # #$.# #\n"
	                 + "    #+     #\n"
	                 + "    ########\n"
	                 + "            \n");
	      maxMove.add(3, 300);  
	      levels.add(4,   
	               "               \n"
	             + "         ######\n"
	             + "       ###    #\n"
	                 + "       #.@$   #\n"
	                 + "       ###  $.#\n"
	                 + "       #.#  $ #\n"
	                 + "       # #  . #\n"
	                 + "       #$  $$.#\n"
	                 + "       # +  . #\n"
	                 + "       ########\n");
	      maxMove.add(4, 300); 
	      levels.add(5,  

	               "        #####      \n"
	               + "        #!  #      \n"
	               + "    ##### $ #      \n"
	               + "    #     $ #      \n" 
	               + "    # $$...####    \n"
	               + "    #   .@.   #    \n"
	               + "    ####...$$ #    \n"
	               + "      # $     #    \n"
	               + "      # $ #   #    \n"
	               + "      #   #####    \n"
	               + "      #####        \n");
	      maxMove.add(5, 300); 
	      
	      levels.add(6, 
	                 "                \n"
	             +   "       #####    \n"
	               + "    ####  !#    \n"
	               + "    #   $  ####\n"
	               + "    # $$      #\n"
	               + "    #@  #$ $# #\n"
	               + "    ### #   # #\n"
	               + "    #   ###   #\n"
	               + "    #  ..... #\n"
	               + "    #########\n");
	      maxMove.add(6, 300); 
	      	      
	      levels.add(7, 
	              "    #############\n"
	               + "    #      @    #\n"
	               + "    # $  $   $  #\n"
	               + "    #   ######  #\n"
	               + "    #  $!    #  #\n"
	               + "    #   $    # ##\n"
	               + "    ##$ ###  #  #\n"
	               + "    #...   $  $##\n"
	               + "    #...  # $   #\n"
	               + "    #...    #   #\n"
	               + "    #############\n");
	      maxMove.add(7, 300); 
	      levels.add(8, 
	              "        #####      \n"
	               + "        #@  #      \n"
	               + "    ##### $ #      \n"
	               + "    #     $ #      \n" 
	               + "    # $$... ###    \n"
	               + "    #   .!.   #    \n"
	               + "    ####...$$ #    \n"
	               + "      # $ #   #    \n"
	               + "      # $ #   #    \n"
	               + "      #   #####    \n"
	               + "      #####        \n");
	      maxMove.add(8, 300); 
	      
	      levels.add(9, 
	                    "       ##############  \n"
	                  + "     ##       #     #  \n"
	                  + "     #   $$$! # #   ## \n"
	                  + "     #  $   $ # #    # \n" 
	                  + "     #  $ # $ # #..# # \n"
	                  + "     #  $$$$$ # #..# # \n"
	                  + "     #  $ # $ # #  # # \n"
	                  + "     #  $   $.#    # # \n"
	                  + "     #   #@# .#      # \n"
	                  + "    ######## .## ##### \n"
	                  + "    #         ... #    \n"
	                  + "    #         ... #    \n"
	                  + "    ######### ... #    \n"
	                  + "            #######    \n" );
	      maxMove.add(9,300);
	}
	public String ShowLevel(int index) {
		String level = levels.get(index);
		return level;
	}
	
	public int getMaxMoving(int index) {
		return this.maxMove.get(index);
	}
}



