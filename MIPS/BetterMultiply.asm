# 
#   Multiplies two 32-bit numbers and prints the result
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
    mtc1 $v0, $f0
    cvt.d.w $f0, $f0


    #prompts the user for the second input
    li $v0, 4
    la $a0, msg2
    syscall 
    li $v0, 5
    syscall
    mtc1 $v0, $f0
    cvt.d.w $f2, $f0

    #multiplies the two numbers together
    la $s1, output
    mul.d $f12, $f0, $f2
    li $v0, 3
    syscall

    #end of program
    li $v0, 10
    syscall
    