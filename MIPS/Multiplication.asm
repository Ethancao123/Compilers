# 
#   Multiplies two numbers and prints the result
#   @author Ethan Cao
#   @version 11/10/21
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
    move $t0, $v0

    #prompts the user for the second input
    li $v0, 4
    la $a0, msg2
    syscall 
    li $v0, 5
    syscall
    move $t1, $v0

    #multiplies the two numbers together
    mult $t0, $t1
    mflo $a0

    #prints the result
    li $v0, 1
    syscall

    #end of program
    li $v0, 10
    syscall
    