# 
#   Checks if a number is even or odd
#   @author Ethan Cao
#   @version 11/10/21
#

    .data   
        prompt: .asciiz "Input the number \n"
        msgEven: .asciiz "\n That number is even"
        msgOdd: .asciiz "\n That number is odd"
    .text 0x00400000
    .globl main

main:
    #ask the user for input
    li $v0, 4
    la $a0, prompt
    syscall 
    li $v0, 5
    syscall
    move $t0, $v0

    #checks if even or odd
    li $t1, 2
    div $t0, $t1
    mflo $t2
    mult $t2, $t1
    mflo $t2
    li $v0, 4
    beq $t0, $t2, even

    #prints odd
    la $a0, msgOdd
    syscall
    j end

even:
    #prints even
    la $a0, msgEven
    syscall

    #end
end:
    li $v0, 10
    syscall
    