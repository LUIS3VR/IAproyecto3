package game;

/**
 *
 * @author eduardo
 */
public class SokobanSet {
    
    /*
    
        # -> wall
        $ -> box
        . -> target
        * -> box over target
        @ -> man
        + -> man over target

        ###
        #@ ##
        #.$* #
        #   ##
        #    #
        ######
    
        SIZE = 6
    
    */
    public static char[][] normal_level_01 = {
                                                {'#','#','#',' ',' ',' '},
                                                {'#','@',' ','#','#',' '},
                                                {'#','.','$','*',' ','#'},
                                                {'#',' ',' ',' ','#','#'},
                                                {'#',' ',' ',' ',' ','#'},
                                                {'#','#','#','#','#','#'},
                                            };
    
    /*
        ####
        #.##
        #  ###
        #*@ ##
        #  $ #
        #    #
        ######
        
        SIZE = 7
    */
    public static char[][] normal_level_02 = {
                                                {'#','#','#','#',' ',' ',' '},
                                                {'#','.','#','#',' ',' ',' '},
                                                {'#',' ',' ','#','#','#',' '},
                                                {'#','*','@',' ','#','#',' '},
                                                {'#',' ',' ','$',' ','#',' '},
                                                {'#',' ',' ',' ',' ','#',' '},
                                                {'#','#','#','#','#','#',' '}
                                             };
    
    /*
       
          ####
        ###  #
        #@ .$##
        #   $ #
        # #.  #
        #     #
        #######
        
        SIZE = 7
    */
    public static char[][] normal_level_03 = {
                                                {' ',' ','#','#','#','#',' '},
                                                {'#','#','#',' ',' ','#',' '},
                                                {'#','@',' ','.','$','#','#'},
                                                {'#',' ',' ',' ','$',' ','#'},
                                                {'#',' ','#','.',' ',' ','#'},
                                                {'#',' ',' ',' ',' ',' ','#'},
                                                {'#','#','#','#','#','#','#'}
                                             };

    /*
       
          #####
        ###   #
        # $ # ##
        # #  . #
        # .  # #
        ##$#.$ #
         #@  ###
         #####
    
        SIZE = 8
    */
    public static char[][] normal_level_04 = {
                                                {' ',' ','#','#','#','#','#',' '},
                                                {'#','#','#',' ',' ',' ','#',' '},
                                                {'#',' ','$',' ','#',' ','#','#'},
                                                {'#',' ','#',' ',' ','.',' ','#'},
                                                {'#',' ','.',' ',' ','#',' ','#'},
                                                {'#','#','$','#','.','$',' ','#'},
                                                {' ','#','@',' ',' ','#','#','#'},
                                                {' ','#','#','#','#','#',' ',' '}
                                             };

    /*
       
        ##########
        #        #
        #  $ @ $ #
        #    $$  #
        #  .. .. #
        ##########
    
        SIZE = 10
    */
    public static char[][] normal_level_05 = {
                                                {'#','#','#','#','#','#','#','#','#','#'},
                                                {'#',' ',' ',' ',' ',' ',' ',' ',' ','#'},
                                                {'#',' ',' ','$',' ','@',' ','$',' ','#'},
                                                {'#',' ',' ',' ',' ','$','$',' ',' ','#'},
                                                {'#',' ',' ','.','.',' ','.','.',' ','#'},
                                                {'#','#','#','#','#','#','#','#','#','#'},
                                                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                                                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                                                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                                                {' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}
                                             };
    
    /*
       
        ##########
        #.       #
        #        #
        #     #  #
        #     # .#
        #     ####
        #$  $    #
        #        #
        #       @#
        ##########
    
        SIZE = 10
    */
    public static char[][] normal_level_06 = {
                                                {'#','#','#','#','#','#','#','#','#','#'},
                                                {'#','.',' ',' ',' ',' ',' ',' ',' ','#'},
                                                {'#',' ',' ',' ',' ',' ',' ',' ',' ','#'},
                                                {'#',' ',' ',' ',' ',' ','#',' ',' ','#'},
                                                {'#',' ',' ',' ',' ',' ','#',' ','.','#'},
                                                {'#',' ',' ',' ',' ',' ','#','#','#','#'},
                                                {'#','$',' ',' ','$',' ',' ',' ',' ','#'},
                                                {'#',' ',' ',' ',' ',' ',' ',' ',' ','#'},
                                                {'#',' ',' ',' ',' ',' ',' ',' ','@','#'},
                                                {'#','#','#','#','#','#','#','#','#','#'}
                                             };

    /*
       
         ####
         #  ###
         #$   #
        ##@$. #
        # $#.##
        #   .#
        #    #
        #    #
        ######
    
        SIZE = 9
    */
    public static char[][] normal_level_07 = {
                                                {' ','#','#','#','#',' ',' ',' ',' '},
                                                {' ','#',' ',' ','#','#','#',' ',' '},
                                                {' ','#','$',' ',' ',' ','#',' ',' '},
                                                {'#','#','@','$','.',' ','#',' ',' '},
                                                {'#',' ','$',' ','.','#','#',' ',' '},
                                                {'#',' ',' ',' ','.','#',' ',' ',' '},
                                                {'#',' ',' ',' ',' ','#',' ',' ',' '},
                                                {'#',' ',' ',' ',' ','#',' ',' ',' '},
                                                {'#','#','#','#','#','#',' ',' ',' '}
                                             };

    /*
       
          ####
          #@ #
        ###$$##
        #    .#
        #   $ #
        ##. ###
         #. #
         ####
    
        SIZE = 8
    */
    public static char[][] normal_level_08 = {
                                                {' ',' ','#','#','#','#',' ',' '},
                                                {' ',' ','#','@',' ','#',' ',' '},
                                                {'#','#','#','$','$','#','#',' '},
                                                {'#',' ',' ',' ',' ','.','#',' '},
                                                {'#',' ',' ',' ','$',' ','#',' '},
                                                {'#','#','.',' ','#','#','#',' '},
                                                {' ','#','.',' ','#',' ',' ',' '},
                                                {' ','#','#','#','#',' ',' ',' '}
                                             };
}//end class