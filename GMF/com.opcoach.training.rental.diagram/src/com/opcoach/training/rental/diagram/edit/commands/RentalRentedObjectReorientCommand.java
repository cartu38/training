package com.opcoach.training.rental.diagram.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientReferenceRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;

import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalObject;
import com.opcoach.training.rental.diagram.edit.policies.RentalBaseItemSemanticEditPolicy;

/**
 * @generated
 */
public class RentalRentedObjectReorientCommand extends EditElementCommand
{

	/**
	 * @generated
	 */
	private final int reorientDirection;

	/**
	 * @generated
	 */
	private final EObject referenceOwner;

	/**
	 * @generated
	 */
	private final EObject oldEnd;

	/**
	 * @generated
	 */
	private final EObject newEnd;

	/**
	 * @generated
	 */
	public RentalRentedObjectReorientCommand(ReorientReferenceRelationshipRequest request)
	{
		super(request.getLabel(), null, request);
		reorientDirection = request.getDirection();
		referenceOwner = request.getReferenceOwner();
		oldEnd = request.getOldRelationshipEnd();
		newEnd = request.getNewRelationshipEnd();
	}

	/**
	 * @generated
	 */
	public boolean canExecute()
	{
		if (false == referenceOwner instanceof Rental)
		{
			return false;
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE)
		{
			return canReorientSource();
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET)
		{
			return canReorientTarget();
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean canReorientSource()
	{
		if (!(oldEnd instanceof RentalObject && newEnd instanceof Rental))
		{
			return false;
		}
		return RentalBaseItemSemanticEditPolicy.LinkConstraints.canExistRentalRentedObject_4002(getNewSource(), getOldTarget());
	}

	/**
	 * @generated
	 */
	protected boolean canReorientTarget()
	{
		if (!(oldEnd instanceof RentalObject && newEnd instanceof RentalObject))
		{
			return false;
		}
		return RentalBaseItemSemanticEditPolicy.LinkConstraints.canExistRentalRentedObject_4002(getOldSource(), getNewTarget());
	}

	/**
	 * @generated
	 */
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException
	{
		if (!canExecute())
		{
			throw new ExecutionException("Invalid arguments in reorient link command"); //$NON-NLS-1$
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE)
		{
			return reorientSource();
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET)
		{
			return reorientTarget();
		}
		throw new IllegalStateException();
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientSource() throws ExecutionException
	{
		getOldSource().setRentedObject(null);
		getNewSource().setRentedObject(getOldTarget());
		return CommandResult.newOKCommandResult(referenceOwner);
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientTarget() throws ExecutionException
	{
		getOldSource().setRentedObject(getNewTarget());
		return CommandResult.newOKCommandResult(referenceOwner);
	}

	/**
	 * @generated
	 */
	protected Rental getOldSource()
	{
		return (Rental) referenceOwner;
	}

	/**
	 * @generated
	 */
	protected Rental getNewSource()
	{
		return (Rental) newEnd;
	}

	/**
	 * @generated
	 */
	protected RentalObject getOldTarget()
	{
		return (RentalObject) oldEnd;
	}

	/**
	 * @generated
	 */
	protected RentalObject getNewTarget()
	{
		return (RentalObject) newEnd;
	}
}