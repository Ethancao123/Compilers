# 
#   Finds the max of 2 numbers
#   @author Ethan Cao
#   @version 12/2/21
#

    .data   
        msg1: .asciiz "Input the first integer \n"
        msg2: .asciiz "Input the second integer \n"
    .text 0x00400000
    .globl main

main:
    #prompts the user for the first input
    li $v0, 4
    la $a0, msg1
    syscall 
    li $v0, 5
    syscall
    move $t1, $v0

    #prompts the user for the second input
    li $v0, 4
    la $a0, msg2
    syscall 
    li $v0, 5
    syscall
    move $a0, $v0
    move $a1, $t1
    #calls the max2 subroutine
    subu $sp, $sp, 4
    sw $ra, ($sp)
    jal max2
    lw $ra, ($sp)
    addu $sp, $sp, 4
    move $a0, $v0
    #prints the result
    li $v0, 1
    syscall

end:
    li $v0, 10
    syscall

max2:
    bgt $a0, $a1, greater
    move $v0, $a1
    jr $ra
greater:
    move $v0, $a0
    jr $ra
    