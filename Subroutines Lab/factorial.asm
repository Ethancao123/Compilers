# 
#   Finds the factorial of a number
#   @author Ethan Cao
#   @version 12/21
#
    .data   
        msg1: .asciiz "Input the value \n"
    .text 0x00400000
    .globl main

main:
    #prompts the user for input
    li $v0, 4
    la $a0, msg1
    syscall 
    li $v0, 5
    syscall
    move $a0, $v0
    #jumps to the factorial subroutine
    subu $sp, $sp, 4
    sw $ra, ($sp)
    jal fact
    lw $ra, ($sp)
    addu $sp, $sp, 4
    move $a0, $v0
    #prints the result
    li $v0, 1
    syscall
end:
    li $v0, 10
    syscall

fact:
    #checks if n has reached zero
    beq $a0, 0, return
    move $t0, $a0
    subu $a0, $a0, 1
    #adds n to the stack
    subu $sp, $sp, 4
    sw $t0, ($sp)
    #adds the return address to the stack
    subu $sp, $sp, 4
    sw $ra, ($sp)
    jal fact
    #gets the return address from the stack
    lw $ra, ($sp)
    addu $sp, $sp, 4
    #gets n from the stack
    lw $t0, ($sp)
    addu $sp, $sp, 4
    #multiplies n with the result
    mult $t0, $v0
    mflo $v0
    jr $ra
return:
    li $v0, 1
    jr $ra
    